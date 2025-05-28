package com.example.capdemo.ai

interface DownloadProgressListener {
    fun onProgressUpdate(progress: Int, bytesDownloaded: Long = 0, totalBytes: Long = 0)
    fun onComplete()
    fun onFailure(error: String)
}