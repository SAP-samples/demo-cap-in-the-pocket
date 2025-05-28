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
import android.os.StatFs

class ModelDownloader(private val context: Context) {

    private val TAG = "ModelDownloader"
    private val MODEL_URL = "https://huggingface.co/litert-community/Qwen2.5-1.5B-Instruct/resolve/main/Qwen2.5-1.5B-Instruct_multi-prefill-seq_q8_ekv1280.task"
    private val MODEL_FILENAME = "model.task"

    fun getModelFile(): File {
        return File(context.filesDir, MODEL_FILENAME)
    }
    
    fun isModelDownloaded(): Boolean {
        val modelFile = getModelFile()
        // Check if file exists AND is a valid zip archive
        if (modelFile.exists() && modelFile.length() > 1000000) {
            return true
        }
        return false
    }
    
    suspend fun downloadModel(listener: DownloadProgressListener? = null): Boolean = withContext(Dispatchers.IO) {

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
            
            // Get actual file size instead of hardcoding
            val fileSize = 1600 * 1000 * 1000L;
            // Check available space
           /* val requiredSpace = fileSize * 2 // Need double space for temp file and final file
            val availableSpace = checkAvailableSpace()
            if (availableSpace < requiredSpace) {
                val required = formatFileSize(requiredSpace)
                val available = formatFileSize(availableSpace)
                val errorMsg = "Not enough storage space. Need $required, but only $available available"
                Log.e(TAG, errorMsg)
                listener?.onFailure(errorMsg)
                return@withContext false
            }*/
            
            val inputStream = connection.inputStream
            val outputStream = FileOutputStream(getModelFile())
            
            val buffer = ByteArray(8192)
            var downloaded = 0L
            var read: Int
            
            Log.d(TAG, "Downloading file of size $fileSize bytes")
            while (inputStream.read(buffer).also { read = it } != -1) {
                outputStream.write(buffer, 0, read)
                downloaded += read
                val progress = (downloaded * 100) / fileSize
                listener?.onProgressUpdate(progress.toInt(), downloaded.toLong(), fileSize.toLong())
            }
            
            outputStream.flush()
            outputStream.close()
            inputStream.close()

            return@withContext true
        } catch (e: Exception) {
            Log.e(TAG, "Error downloading model", e)
            listener?.onFailure(e.message ?: "Unknown error")
            return@withContext false
        }
    }

    // Add this method to check available space
    private fun checkAvailableSpace(): Long {
        val statFs = StatFs(context.filesDir.absolutePath)
        return statFs.availableBytes
    }

    // Add this utility method to format file size
    private fun formatFileSize(size: Long): String {
        val units = arrayOf("B", "KB", "MB", "GB")
        var fileSize = size.toFloat()
        var unitIndex = 0
        
        while (fileSize > 1024 && unitIndex < units.size - 1) {
            fileSize /= 1024
            unitIndex++
        }
        
        return String.format("%.1f %s", fileSize, units[unitIndex])
    }
}