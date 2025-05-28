package com.example.capdemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.capdemo.viewmodel.AIViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MainActivity : AppCompatActivity() {
    private lateinit var inputEditText: EditText
    private lateinit var generateButton: Button
    private lateinit var outputTextView: TextView
    private lateinit var statusTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var downloadProgressBar: ProgressBar
    private lateinit var downloadStatusTextView: TextView
    
    private lateinit var viewModel: AIViewModel
    private val activityScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Initialize views
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
        
        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[AIViewModel::class.java]
        
        // Observe ViewModel state
        setupObservers()
        
        // Disable button until model is ready
        generateButton.isEnabled = false
        
        // Start model initialization if needed
        viewModel.checkModelAndInitialize()
        
        generateButton.setOnClickListener {
            val prompt = inputEditText.text.toString().trim()
            if (prompt.isNotEmpty()) {
                processPrompt(prompt)
            }
        }
    }
    
    private fun setupObservers() {
        // Observe download progress
        viewModel.downloadProgress.observe(this) { progress ->
            downloadProgressBar.progress = progress
        }
        
        // Observe download status
        viewModel.downloadStatus.observe(this) { status ->
            when (status) {
                AIViewModel.DownloadStatus.NOT_STARTED -> {
                    downloadStatusTextView.text = getString(R.string.initializing_model)
                }
                AIViewModel.DownloadStatus.IN_PROGRESS -> {
                    downloadStatusTextView.visibility = View.VISIBLE
                    downloadProgressBar.visibility = View.VISIBLE
                    downloadProgressBar.isIndeterminate = false
                }
                AIViewModel.DownloadStatus.COMPLETED -> {
                    downloadStatusTextView.text = getString(R.string.download_complete)
                    Handler(Looper.getMainLooper()).postDelayed({
                        downloadStatusTextView.visibility = View.GONE
                        downloadProgressBar.visibility = View.GONE
                    }, 2000)
                }
                AIViewModel.DownloadStatus.FAILED -> {
                    downloadStatusTextView.text = getString(R.string.download_failed, "Unknown error")
                    Toast.makeText(this, "Model download failed", Toast.LENGTH_LONG).show()
                }
                AIViewModel.DownloadStatus.SPACE_ERROR -> {
                    downloadStatusTextView.text = getString(R.string.no_space_error)
                    Toast.makeText(this, "Not enough space to download model", Toast.LENGTH_LONG).show()
                }
            }
        }
        
        // Observe time estimate
        viewModel.downloadTimeEstimate.observe(this) { estimate ->
            if (viewModel.downloadStatus.value == AIViewModel.DownloadStatus.IN_PROGRESS) {
                val progress = viewModel.downloadProgress.value ?: 0
                downloadStatusTextView.text = getString(R.string.downloading_model_with_estimate, progress, estimate)
            }
        }
        
        // Observe model initialization
        viewModel.modelInitialized.observe(this) { initialized ->
            if (initialized) {
                generateButton.isEnabled = true
                statusTextView.text = getString(R.string.model_ready)
                progressBar.visibility = View.GONE
            } else {
                generateButton.isEnabled = false
                if (viewModel.downloadStatus.value != AIViewModel.DownloadStatus.IN_PROGRESS) {
                    statusTextView.text = getString(R.string.model_init_failed)
                }
            }
        }
    }
    
    private fun processPrompt(prompt: String) {
        // Show loading state
        generateButton.isEnabled = false
        progressBar.visibility = View.VISIBLE
        statusTextView.text = getString(R.string.processing)
        
        viewModel.generateResponse(prompt).observe(this) { result ->
            when (result) {
                is AIViewModel.GenerationResult.Success -> {
                    outputTextView.text = result.text
                    statusTextView.text = getString(R.string.generated_in, result.processingTimeMs)
                }
                is AIViewModel.GenerationResult.Error -> {
                    outputTextView.text = result.error
                    statusTextView.text = getString(R.string.processing_failed)
                }
            }
            progressBar.visibility = View.GONE
            generateButton.isEnabled = true
        }
    }
}