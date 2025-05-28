package com.example.capdemo.ai

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.zip.ZipFile
import java.util.zip.ZipException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ModelDownloader(private val context: Context) {

    private val TAG = "ModelDownloader"
    private val MODEL_URL = "https://huggingface.co/litert-community/Phi-4-mini-instruct/resolve/main/Phi-4-mini-instruct_multi-prefill-seq_q8_ekv1280.task"
    private val MODEL_FILENAME = "model.task"

    fun getModelFile(): File {
        return File(context.filesDir, MODEL_FILENAME)
    }
    
    fun isModelDownloaded(): Boolean {
        val modelFile = getModelFile()
        // Check if file exists AND is a valid zip archive
        if (modelFile.exists() && modelFile.length() > 0) {
            try {
                // Try to open it as a zip to verify integrity
                ZipFile(modelFile).close()
                return true
            } catch (e: Exception) {
                Log.e(TAG, "Model file exists but is not a valid zip archive", e)
                // Delete corrupted file
                modelFile.delete()
                return false
            }
        }
        return false
    }
    
    suspend fun downloadModel(listener: DownloadProgressListener? = null): Boolean = withContext(Dispatchers.IO) {
        val modelFile = getModelFile()
        val tempFile = File(context.filesDir, "$MODEL_FILENAME.tmp")
        
        try {
            Log.d(TAG, "Starting model download from $MODEL_URL")
            val connection = URL(MODEL_URL).openConnection() as HttpURLConnection
            connection.connectTimeout = 30000
            connection.readTimeout = 30000
            connection.connect()
            
            if (connection.responseCode != HttpURLConnection.HTTP_OK) {
                Log.e(TAG, "Server returned HTTP ${connection.responseCode}")
                listener?.onFailure("Server returned HTTP ${connection.responseCode}")
                return@withContext false
            }
            
            val fileSize = 4000000000;
            val inputStream = connection.inputStream
            val outputStream = FileOutputStream(tempFile)
            
            val buffer = ByteArray(8192)
            var downloaded = 0L
            var read: Int
            
            Log.d(TAG, "Downloading file of size $fileSize bytes")
            var lastProgress = 0
            while (inputStream.read(buffer).also { read = it } != -1) {
                outputStream.write(buffer, 0, read)
                downloaded += read
                val progress = (downloaded * 100) / fileSize
                listener?.onProgressUpdate(progress.toInt(), downloaded.toLong(), fileSize.toLong())
            }
            
            outputStream.flush()
            outputStream.close()
            inputStream.close()
            
            // Verify download by trying to open as zip
            try {
                ZipFile(tempFile).close()
                // If successful, move temp file to final location
                if (modelFile.exists()) {
                    modelFile.delete()
                }
                
                if (tempFile.renameTo(modelFile)) {
                    Log.d(TAG, "Model downloaded successfully and verified as valid zip archive")
                    listener?.onComplete()
                    return@withContext true
                } else {
                    Log.e(TAG, "Failed to rename temp file to final model file")
                    listener?.onFailure("Failed to save model file")
                    return@withContext false
                }
            } catch (e: ZipException) {
                Log.e(TAG, "Downloaded file is not a valid zip archive", e)
                tempFile.delete()
                listener?.onFailure("Downloaded file is not a valid zip archive")
                return@withContext false
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error downloading model", e)
            tempFile.delete()
            listener?.onFailure(e.message ?: "Unknown error")
            return@withContext false
        }
    }
}