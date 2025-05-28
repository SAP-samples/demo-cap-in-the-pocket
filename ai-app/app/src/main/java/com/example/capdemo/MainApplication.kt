package com.example.capdemo

import android.app.Application
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

import com.example.capdemo.ai.AIMessageProcessor
import com.example.capdemo.ai.ModelDownloader
import com.example.capdemo.service.BackgroundService

class MainApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    lateinit var aiMessageProcessor: AIMessageProcessor
    private lateinit var modelDownloader: ModelDownloader

    companion object {
        private const val TAG = "MainApplication"
        private var instance: MainApplication? = null
        
        fun getInstance(): MainApplication {
            return instance ?: throw IllegalStateException("Application instance not available")
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        
        Log.d(TAG, "Initializing application components")
        
        // Initialize components first
        modelDownloader = ModelDownloader(this)
        aiMessageProcessor = AIMessageProcessor(this)
        
        // THEN call preload (not before initialization)
        preloadModel()
        
        // Start the background service
        startBackgroundService()
    }
    
    private fun startBackgroundService() {
        Log.d(TAG, "Starting background service")
        val serviceIntent = Intent(this, BackgroundService::class.java)
        
        // Use appropriate method to start the service based on Android version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
    }
    
    // Pre-download the model for faster first use
    fun preloadModel() {
        // Don't preload here - let the UI handle it
        // This is causing a race condition with the UI
        Log.d(TAG, "Model preloading deferred to UI")
    }
    
    fun getModelDownloader(): ModelDownloader {
        return modelDownloader
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "Application terminating")
        instance = null
    }
}