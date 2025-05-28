package com.example.capdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.capdemo.MainApplication
import com.example.capdemo.ai.AIMessageProcessor
import com.example.capdemo.ai.DownloadProgressListener
import com.example.capdemo.ai.ModelDownloader
import kotlinx.coroutines.launch

class AIViewModel(application: Application) : AndroidViewModel(application) {
    
    private val modelDownloader: ModelDownloader
    private val aiMessageProcessor: AIMessageProcessor
    
    // Model state
    private val _downloadProgress = MutableLiveData<Int>()
    val downloadProgress: LiveData<Int> = _downloadProgress
    
    private val _downloadStatus = MutableLiveData<DownloadStatus>()
    val downloadStatus: LiveData<DownloadStatus> = _downloadStatus
    
    private val _downloadTimeEstimate = MutableLiveData<String>()
    val downloadTimeEstimate: LiveData<String> = _downloadTimeEstimate
    
    private val _modelInitialized = MutableLiveData<Boolean>()
    val modelInitialized: LiveData<Boolean> = _modelInitialized
    
    private var downloadStartTime: Long = 0
    private var lastTimeEstimateUpdateTime: Long = 0
    private val TIME_ESTIMATE_UPDATE_INTERVAL_MS = 10000 // 10 seconds
    
    init {
        val app = application as MainApplication
        modelDownloader = app.getModelDownloader()
        aiMessageProcessor = app.aiMessageProcessor
        
        // Set initial states
        _downloadStatus.value = DownloadStatus.NOT_STARTED
        _modelInitialized.value = false
        
        // Check if model is already initialized
        if (aiMessageProcessor.isModelLoaded()) {
            _modelInitialized.value = true
        }
    }
    
    fun checkModelAndInitialize() {
        viewModelScope.launch {
            // Only start download if not already in progress or completed
            if (_downloadStatus.value == DownloadStatus.NOT_STARTED || 
                _downloadStatus.value == DownloadStatus.FAILED) {
                
                if (!modelDownloader.isModelDownloaded()) {
                    downloadModel()
                } else {
                    initializeModel()
                }
            }
        }
    }
    
    private fun downloadModel() {
        viewModelScope.launch {
            downloadStartTime = System.currentTimeMillis()
            _downloadStatus.value = DownloadStatus.IN_PROGRESS
            
            try {
                modelDownloader.downloadModel(object : DownloadProgressListener {
                    override fun onProgressUpdate(progress: Int, bytesDownloaded: Long, totalBytes: Long) {
                        _downloadProgress.postValue(progress)
                        
                        // Calculate time estimate only every 10 seconds
                        val currentTime = System.currentTimeMillis()
                        if (currentTime - lastTimeEstimateUpdateTime >= TIME_ESTIMATE_UPDATE_INTERVAL_MS) {
                            // Calculate elapsed seconds since download started
                            val elapsedSeconds = (currentTime - downloadStartTime) / 1000
                            if (elapsedSeconds > 0 && totalBytes > 0) {
                                val downloadSpeed = bytesDownloaded / elapsedSeconds
                                val remainingBytes = totalBytes - bytesDownloaded
                                val remainingSeconds = if (downloadSpeed > 0) remainingBytes / downloadSpeed else 0
                                _downloadTimeEstimate.postValue(formatTimeEstimate(remainingSeconds))
                            }
                            
                            // Update the timestamp
                            lastTimeEstimateUpdateTime = currentTime
                        }
                    }
                    
                    override fun onComplete() {
                        _downloadStatus.postValue(DownloadStatus.COMPLETED)
                        initializeModel()
                    }
                    
                    override fun onFailure(error: String) {
                        if (error.contains("Not enough storage space")) {
                            _downloadStatus.postValue(DownloadStatus.SPACE_ERROR)
                        } else {
                            _downloadStatus.postValue(DownloadStatus.FAILED)
                        }
                    }
                })
            } catch (e: Exception) {
                _downloadStatus.postValue(DownloadStatus.FAILED)
            }
        }
    }
    
    fun initializeModel() {
        viewModelScope.launch {
            _downloadStatus.value = DownloadStatus.COMPLETED
            
            if (aiMessageProcessor.initialize()) {
                _modelInitialized.value = true
            } else {
                _downloadStatus.value = DownloadStatus.FAILED
            }
        }
    }
    
    fun generateResponse(prompt: String): LiveData<GenerationResult> {
        val result = MutableLiveData<GenerationResult>()
        
        viewModelScope.launch {
            try {
                val response = aiMessageProcessor.generateResponse(prompt)
                result.value = GenerationResult.Success(response.response, response.processingTimeMs)
            } catch (e: Exception) {
                result.value = GenerationResult.Error("Error: ${e.message}")
            }
        }
        
        return result
    }
    
    private fun formatTimeEstimate(seconds: Long): String {
        return when {
            seconds < 60 -> "less than a minute remaining"
            seconds < 3600 -> {
                // Round up to the nearest minute to prevent frequent changes
                val minutes = (seconds + 59) / 60 // This rounds up
                if (minutes == 1L) "about 1 minute remaining" else "about $minutes minutes remaining"
            }
            else -> {
                // For hours, round to nearest half hour
                val hours = seconds / 3600.0
                when {
                    hours < 1.25 -> "about 1 hour remaining"
                    hours < 1.75 -> "about 1.5 hours remaining"
                    hours < 2.25 -> "about 2 hours remaining"
                    hours < 2.75 -> "about 2.5 hours remaining"
                    hours < 3.5 -> "about 3 hours remaining" 
                    hours < 4.5 -> "about 4 hours remaining"
                    hours < 5.5 -> "about 5 hours remaining"
                    else -> "over 5 hours remaining"
                }
            }
        }
    }
    
    // Status enum for download state
    enum class DownloadStatus {
        NOT_STARTED,
        IN_PROGRESS,
        COMPLETED,
        FAILED,
        SPACE_ERROR
    }
    
    // Sealed class for generation results
    sealed class GenerationResult {
        data class Success(val text: String, val processingTimeMs: Long) : GenerationResult()
        data class Error(val error: String) : GenerationResult()
    }
}