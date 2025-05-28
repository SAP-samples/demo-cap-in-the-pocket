package com.example.capdemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.capdemo.ai.AIMessageProcessor
import com.example.capdemo.ai.DownloadProgressListener
import com.example.capdemo.ai.ModelDownloader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var inputEditText: EditText
    private lateinit var generateButton: Button
    private lateinit var outputTextView: TextView
    private lateinit var statusTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var downloadProgressBar: ProgressBar
    private lateinit var downloadStatusTextView: TextView
    private lateinit var modelDownloader: ModelDownloader
    
    private lateinit var aiMessageProcessor: AIMessageProcessor
    private val activityScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Initialize views FIRST
        inputEditText = findViewById(R.id.inputEditText)
        generateButton = findViewById(R.id.generateButton)
        outputTextView = findViewById(R.id.outputTextView)
        statusTextView = findViewById(R.id.statusTextView)
        progressBar = findViewById(R.id.progressBar)
        downloadProgressBar = findViewById(R.id.downloadProgressBar)
        downloadStatusTextView = findViewById(R.id.downloadStatusTextView)
        
        // Set the info text with HTML formatting
        val infoTextView = findViewById<TextView>(R.id.infoTextView)
        infoTextView.text = Html.fromHtml(getString(R.string.app_info), Html.FROM_HTML_MODE_COMPACT)
        
        // Disable button until model is ready
        generateButton.isEnabled = false
        
        // Get components from application
        val app = application as MainApplication
        aiMessageProcessor = app.aiMessageProcessor
        modelDownloader = app.getModelDownloader()
        
        // Start model check and initialization
        checkModelAndInitialize()
        
        generateButton.setOnClickListener {
            val prompt = inputEditText.text.toString().trim()
            if (prompt.isNotEmpty()) {
                processPrompt(prompt)
            }
        }
    }
    
    private fun processPrompt(prompt: String) {
        // Show loading state
        generateButton.isEnabled = false
        progressBar.visibility = View.VISIBLE
        statusTextView.text = getString(R.string.processing)
        
        activityScope.launch {
            try {
                // Process the message using AIMessageProcessor
                val result = aiMessageProcessor.generateResponse(prompt)
                
                // Update UI with result
                if (result.response.startsWith("Error:")) {
                    outputTextView.text = result.response
                    statusTextView.text = getString(R.string.processing_failed)
                } else {
                    outputTextView.text = result.response
                    statusTextView.text = getString(R.string.generated_in, result.processingTimeMs)
                }
            } catch (e: Exception) {
                outputTextView.text = "Error: ${e.message}"
                statusTextView.text = getString(R.string.processing_failed)
            } finally {
                // Hide loading state
                progressBar.visibility = View.GONE
                generateButton.isEnabled = true
            }
        }
    }
    
    private fun downloadModel() {
        // Make sure we're on the UI thread when changing visibility
        runOnUiThread {
            // FIRST set visibility BEFORE starting download
            downloadStatusTextView.visibility = View.VISIBLE
            downloadProgressBar.visibility = View.VISIBLE
            downloadProgressBar.progress = 0
            downloadProgressBar.max = 100
            downloadProgressBar.isIndeterminate = false
            
            // Use a solid color to see if it's visible
            downloadProgressBar.progressDrawable.setColorFilter(
                resources.getColor(android.R.color.holo_blue_bright, theme),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            
            // Log to verify
            Log.d("MainActivity", "Download progress bar visibility: ${downloadProgressBar.visibility}")
        }
        
        // For time estimation
        val startTime = System.currentTimeMillis()
        var lastProgressUpdate = 0
        
        // THEN start download
        activityScope.launch {
            try {
                modelDownloader.downloadModel(object : DownloadProgressListener {
                    override fun onProgressUpdate(progress: Int, bytesDownloaded: Long, totalBytes: Long) {
                        runOnUiThread {
                            downloadProgressBar.progress = progress
                            if (progress == lastProgressUpdate) {
                                return@runOnUiThread
                            }
                            // Calculate time estimate only when we have meaningful progress
                            if (progress > 0) {
                                lastProgressUpdate = progress
                                
                                val elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000
                                if (elapsedSeconds > 0) {
                                    // Calculate speed (bytes per second)
                                    val downloadSpeed = bytesDownloaded / elapsedSeconds
                                    
                                    // Calculate remaining time
                                    val remainingBytes = totalBytes - bytesDownloaded
                                    val remainingSeconds = if (downloadSpeed > 0) remainingBytes / downloadSpeed else 0
                                    
                                    // Format the time estimate
                                    val timeEstimate = formatTimeEstimate(remainingSeconds)
                                    
                                    downloadStatusTextView.text = getString(
                                        R.string.downloading_model_with_estimate, 
                                        progress, timeEstimate
                                    )
                                } else {
                                    downloadStatusTextView.text = getString(R.string.downloading_model, progress)
                                }
                            } else {
                                downloadStatusTextView.text = getString(R.string.downloading_model, progress)
                            }
                        }
                    }
                    
                    override fun onComplete() {
                        runOnUiThread {
                            Log.d("MainActivity", "Download complete")
                            downloadStatusTextView.text = getString(R.string.download_complete)
                            
                            // Wait a bit before hiding
                            Handler(Looper.getMainLooper()).postDelayed({
                                downloadStatusTextView.visibility = View.GONE
                                downloadProgressBar.visibility = View.GONE
                                initializeModel()
                            }, 2000)
                        }
                    }
                    
                    override fun onFailure(error: String) {
                        runOnUiThread {
                            Log.e("MainActivity", "Download failed: $error")
                            downloadStatusTextView.text = getString(R.string.download_failed, error)
                            // Keep visible so user can see the error
                        }
                    }
                })
            } catch (e: Exception) {
                runOnUiThread {
                    Log.e("MainActivity", "Error during download", e)
                    downloadStatusTextView.text = getString(R.string.download_failed, e.message)
                    Toast.makeText(this@MainActivity, "Download error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    
    // Helper function to format time estimate
    private fun formatTimeEstimate(seconds: Long): String {
        return when {
            seconds < 60 -> "$seconds seconds"
            seconds < 3600 -> "${seconds / 60} minutes, ${seconds % 60} seconds"
            else -> "${seconds / 3600} hours, ${(seconds % 3600) / 60} minutes"
        }
    }
    
    private fun checkModelAndInitialize() {
        lifecycleScope.launch {
            if (!modelDownloader.isModelDownloaded()) {
                showDownloadUI()
                downloadModel()
            } else {
                initializeModel()
            }
        }
    }
    
    private fun showDownloadUI() {
        runOnUiThread {
            downloadStatusTextView.visibility = View.VISIBLE
            downloadProgressBar.visibility = View.VISIBLE
            downloadProgressBar.progress = 0
            downloadStatusTextView.text = getString(R.string.downloading_model, 0)
        }
    }
    
    private fun initializeModel() {
        runOnUiThread {
            // Hide download UI
            downloadStatusTextView.visibility = View.GONE
            downloadProgressBar.visibility = View.GONE
            
            // Show initialization UI
            statusTextView.text = getString(R.string.initializing_model)
            progressBar.visibility = View.VISIBLE
        }
        
        // Continue with model initialization...
        lifecycleScope.launch {
            // Initialize model code...
        }
    }
}