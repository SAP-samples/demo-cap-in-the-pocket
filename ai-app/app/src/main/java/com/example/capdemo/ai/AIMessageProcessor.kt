package com.example.capdemo.ai

import android.content.Context
import android.util.Log
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.genai.llminference.LlmInference
import com.google.mediapipe.tasks.genai.llminference.LlmInference.LlmInferenceOptions
import com.google.mediapipe.tasks.genai.llminference.LlmInference.createFromOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.concurrent.atomic.AtomicBoolean
import java.net.HttpURLConnection
import java.net.URL
import java.io.FileOutputStream

class AIMessageProcessor(private val context: Context) {

    val MAX_TOKENS = 50;

    private var textGenerator: LlmInference? = null
    private val modelDownloader = ModelDownloader(context)
    private val isInitialized = AtomicBoolean(false)
    private val isInitializing = AtomicBoolean(false)
    
    /**
     * Checks if the model is already loaded without attempting to initialize it
     */
    fun isModelLoaded(): Boolean {
        return isInitialized.get() && textGenerator != null
    }

    /**
     * Initializes the text generator with the downloaded model
     * Will keep the model loaded between calls
     */
    suspend fun initialize(): Boolean = withContext(Dispatchers.IO) {
        if (isInitialized.get() && textGenerator != null) {
            Log.d(TAG, "Model already initialized and loaded")
            return@withContext true
        }

        if (isInitializing.getAndSet(true)) {
            Log.d(TAG, "Model initialization already in progress, waiting...")
            // Wait until initialization completes elsewhere
            while (isInitializing.get() && !isInitialized.get()) {
                Thread.sleep(100)
            }
            return@withContext isInitialized.get()
        }

        var attemptCount = 0
        val maxAttempts = 2 // Initial attempt + 1 retry
        
        while (attemptCount < maxAttempts && !isInitialized.get()) {
            attemptCount++
            try {
                Log.d(TAG, "Starting model initialization (attempt $attemptCount)")
                
                // First ensure model is downloaded
                if (!modelDownloader.isModelDownloaded() && !modelDownloader.downloadModel()) {
                    Log.e(TAG, "Failed to download model")
                    if (attemptCount >= maxAttempts) {
                        isInitializing.set(false)
                        return@withContext false
                    } else {
                        continue // Try again
                    }
                }

                val modelFile = modelDownloader.getModelFile()
                if (!modelFile.exists()) {
                    Log.e(TAG, "Model file doesn't exist after download")
                    if (attemptCount >= maxAttempts) {
                        isInitializing.set(false)
                        return@withContext false
                    } else {
                        continue // Try again
                    }
                }

                // Configure MediaPipe TextGenerator with model caching enabled
                val options = LlmInferenceOptions.builder()
                    .setModelPath(modelFile.absolutePath)
                    .setPreferredBackend(LlmInference.Backend.DEFAULT)
                    .setMaxTokens(MAX_TOKENS)
                    .build()

                Log.d(TAG, "Creating TextGenerator instance with model")
                textGenerator = createFromOptions(context, options)
                isInitialized.set(true)
                Log.d(TAG, "TextGenerator initialized successfully and model loaded")
                
            } catch (e: Exception) {
                Log.e(TAG, "Error initializing text generator", e)
                
                // Check for zip archive error
                if (e.message?.contains("Unable to open zip archive") == true ||
                    e.toString().contains("Unable to open zip archive")) {
                    
                    Log.w(TAG, "Detected corrupt model file, deleting and attempting redownload")
                    
                    // Delete corrupt model file
                    val modelFile = modelDownloader.getModelFile()
                    if (modelFile.exists()) {
                        modelFile.delete()
                        Log.d(TAG, "Deleted corrupt model file")
                    }
                    
                    // If this was our last attempt, report failure
                    if (attemptCount >= maxAttempts) {
                        Log.e(TAG, "Max retry attempts reached, giving up")
                        isInitializing.set(false)
                        return@withContext false
                    }
                    
                    // Otherwise continue to next attempt (which will trigger redownload)
                    continue
                } else {
                    // For other errors, don't retry
                    isInitializing.set(false)
                    return@withContext false
                }
            }
        }

        isInitializing.set(false)
        return@withContext isInitialized.get()
    }

    /**
     * Processes a text prompt and returns generated response
     * Will maintain model in memory between calls
     */
    suspend fun generateResponse(
        prompt: String
    ): GenerationResult = withContext(Dispatchers.Default) {
        
        if (!isInitialized.get() && !initialize()) {
            return@withContext GenerationResult(
                response = "Error: Model not initialized",
                processingTimeMs = 0
            )
        }
        
        try {
            Log.d(TAG, "Processing prompt: ${prompt.take(50)}...")
            val startTime = System.currentTimeMillis()

            val textGenerationResult = textGenerator?.generateResponse(
                prompt
            )
            
            val endTime = System.currentTimeMillis()
            val processingTime = endTime - startTime
            
            if (textGenerationResult != null) {
                Log.d(TAG, "Generated tokens in ${processingTime}ms")
                GenerationResult(
                    response = textGenerationResult,
                    processingTimeMs = processingTime
                )
            } else {
                Log.e(TAG, "Text generation returned null result")
                GenerationResult(
                    response = "Error: Failed to generate text",
                    processingTimeMs = processingTime
                )
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error generating text", e)
            GenerationResult(
                response = "Error: ${e.message}",
                processingTimeMs = 0
            )
        }
    }
    
    /**
     * Releases resources when the processor is no longer needed
     * Only call this when the app is truly shutting down
     */
    fun shutdown() {
        Log.d(TAG, "Shutting down AIMessageProcessor and releasing model")
        textGenerator?.close()
        textGenerator = null
        isInitialized.set(false)
    }
    
    companion object {
        private const val TAG = "AIMessageProcessor"
    }

    /**
     * Data class to hold the results of text generation
     */
    data class GenerationResult(
        val response: String,
        val processingTimeMs: Long
    )

    fun isModelDownloaded(): Boolean {
        // Check if model file exists
        val modelFile = File(context.filesDir, "model.bin")
        return modelFile.exists()
    }

    suspend fun downloadModel(listener: DownloadProgressListener) = withContext(Dispatchers.IO) {
        try {
            val modelUrl = "https://your-server.com/model.bin"
            val connection = URL(modelUrl).openConnection() as HttpURLConnection
            connection.connect()
            
            val fileSize = connection.contentLength
            val modelFile = File(context.filesDir, "model.bin")
            val inputStream = connection.inputStream
            val outputStream = FileOutputStream(modelFile)
            
            val buffer = ByteArray(8192)
            var downloaded = 0
            var read: Int
            
            while (inputStream.read(buffer).also { read = it } != -1) {
                outputStream.write(buffer, 0, read)
                downloaded += read
                val progress = (downloaded * 100) / fileSize
                listener.onProgressUpdate(progress)
            }
            
            outputStream.flush()
            outputStream.close()
            inputStream.close()
            
            listener.onComplete()
            true
        } catch (e: Exception) {
            listener.onFailure(e.message ?: "Unknown error")
            false
        }
    }
}