package com.example.capdemo.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.capdemo.R
import com.example.capdemo.ai.AIMessageProcessor
import fi.iki.elonen.NanoHTTPD
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.lang.reflect.Method

class BackgroundService : Service() {
    private lateinit var aiMessageProcessor: AIMessageProcessor
    private var nanoHttpdServer: AppNanoHttpd? = null // Use AppNanoHttpd
    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)
    private val PORT = 8087

    companion object {
        private const val TAG = "BackgroundService"
        private const val CHANNEL_ID = "AIServiceChannel"
        private const val NOTIFICATION_ID = 1
        private const val GENERATE_ENDPOINT = "/generate"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service created")

        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification())

        // Initialize AI processor
        aiMessageProcessor = AIMessageProcessor(applicationContext)

        // Start downloading the model immediately
        serviceScope.launch {
            try {
                val initialized = aiMessageProcessor.initialize()
                Log.d(TAG, "Model initialization completed: $initialized")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to initialize AI model", e)
            }
        }

        // Start NanoHTTPD server
        try {
            if (nanoHttpdServer == null || nanoHttpdServer?.isAlive == false) {
                nanoHttpdServer = AppNanoHttpd(PORT, aiMessageProcessor, serviceScope)
                nanoHttpdServer?.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
                Log.d(TAG, "NanoHTTPD server started on port $PORT")
            }
        } catch (e: IOException) {
            Log.e(TAG, "Failed to start NanoHTTPD server", e)
        }
    }

    // Inner class for the NanoHTTPD server implementation
    private class AppNanoHttpd(
        port: Int,
        private val aiProcessor: AIMessageProcessor,
        private val scope: CoroutineScope
    ) : NanoHTTPD(port) {

        override fun serve(session: IHTTPSession): Response {
            val method = session.method
            val uri = session.uri

            if (uri == GENERATE_ENDPOINT && method == Method.POST) {
                val files = HashMap<String, String>()
                try {
                    // For POST requests, NanoHTTPD requires parsing the request body
                    // This is a simplified way to get the body for JSON.
                    // For larger bodies or form data, session.parseBody(files) is needed.
                    session.parseBody(files) // Needed to populate session.getInputStream() for some cases or get files
                    val requestBody = session.queryParameterString // For simple POST this might have the body if not form-data

                    // A more robust way to get the JSON body if it's sent directly
                    val bodyMap = mutableMapOf<String, String>()
                    session.parseBody(bodyMap) // Fills the map with form data, for JSON you need to read the input stream
                    val postBody = bodyMap["postData"] //This key is used by NanoHTTPD when it puts the whole body into the map

                    Log.d(TAG, "Received request on NanoHTTPD: $uri, Method: $method")
                    Log.d(TAG, "Request Body (from queryParameterString): $requestBody")
                    Log.d(TAG, "Request Body (from postData): $postBody")


                    // Choose the correct source for the JSON body
                    // If sending JSON directly (e.g. from curl with -d '{...}'),
                    // 'postBody' (after session.parseBody(bodyMap)) is often where it ends up.
                    // If it's URL-encoded form data, session.parameters will have it.
                    val actualRequestBody = postBody ?: requestBody // Fallback, adjust based on how clients send data

                    if (actualRequestBody == null) {
                        return newFixedLengthResponse(
                            Response.Status.BAD_REQUEST,
                            "application/json",
                            createErrorJson("Request body is empty").toString()
                        )
                    }


                    try {
                        val jsonRequest = JSONObject(actualRequestBody)
                        val prompt = jsonRequest.getString("prompt")

                        // NanoHTTPD's serve method blocks, so AI processing needs to be async
                        // However, we must return a Response object synchronously.
                        // This means we can't directly launch a coroutine and return its result.
                        // For a simple case, we can block here, but for true async,
                        // you'd need a more complex setup (e.g., return a placeholder and update later,
                        // or use libraries that support async request handling with NanoHTTPD).
                        // For this example, we'll make it blocking for simplicity,
                        // but be aware this ties up a NanoHTTPD worker thread.

                        // For true async, you'd typically return a response immediately
                        // and then do work in the background. NanoHTTPD doesn't have a direct
                        // "suspend and resume" mechanism like Ktor or other frameworks.
                        // Let's keep your existing coroutine launch for AI processing
                        // but understand the HTTP response itself will be sent by this thread.

                        // Since we must return a response, we'll run the AI generation
                        // synchronously within the scope of this request for now.
                        // For better performance, consider if your AI processing can be
                        // made faster or if the client can handle a slight delay.
                        try {
                            // This part is now synchronous within the serve method's thread
                            val result: AIMessageProcessor.GenerationResult = kotlinx.coroutines.runBlocking(scope.coroutineContext) { // Use the serviceScope
                                aiProcessor.generateResponse(
                                    prompt
                                )
                            }


                            val jsonResponse = JSONObject()
                            jsonResponse.put("response", result.response)
                            jsonResponse.put("processing_time_ms", result.processingTimeMs)

                            return newFixedLengthResponse(
                                Response.Status.OK,
                                "application/json",
                                jsonResponse.toString()
                            )
                        } catch (e: Exception) {
                            Log.e(TAG, "Error processing AI request in NanoHTTPD", e)
                            return newFixedLengthResponse(
                                Response.Status.INTERNAL_ERROR,
                                "application/json",
                                createErrorJson(e.message ?: "Error processing request").toString()
                            )
                        }

                    } catch (e: JSONException) {
                        Log.e(TAG, "Invalid JSON request in NanoHTTPD", e)
                        return newFixedLengthResponse(
                            Response.Status.BAD_REQUEST,
                            "application/json",
                            createErrorJson("Invalid JSON request: ${e.message}").toString()
                        )
                    }
                } catch (ioe: IOException) {
                    Log.e(TAG, "IOException reading request body in NanoHTTPD", ioe)
                    return newFixedLengthResponse(
                        Response.Status.INTERNAL_ERROR,
                        "application/json",
                        createErrorJson("Error reading request body").toString()
                    )
                } catch (re: ResponseException) {
                    Log.e(TAG, "ResponseException in NanoHTTPD", re)
                    return newFixedLengthResponse(
                        re.status,
                        MIME_PLAINTEXT, // NanoHTTPD's default
                        re.message
                    )
                }
            } else if (uri == GENERATE_ENDPOINT && method != Method.POST) {
                return newFixedLengthResponse(
                    Response.Status.METHOD_NOT_ALLOWED,
                    "application/json",
                    createErrorJson("Method not allowed. Use POST.").toString()
                )
            }

            // Default response for unhandled routes
            return newFixedLengthResponse(
                Response.Status.NOT_FOUND,
                "application/json",
                createErrorJson("Endpoint not found").toString()
            )
        }

        private fun createErrorJson(message: String): JSONObject {
            val errorJson = JSONObject()
            errorJson.put("error", message)
            return errorJson
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service started command")
        // Ensure server is started if not already (e.g., if service is restarted)
        if (nanoHttpdServer == null || nanoHttpdServer?.isAlive == false) {
            try {
                nanoHttpdServer = AppNanoHttpd(PORT, aiMessageProcessor, serviceScope)
                nanoHttpdServer?.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
                Log.d(TAG, "NanoHTTPD server restarted on port $PORT")
            } catch (e: IOException) {
                Log.e(TAG, "Failed to restart NanoHTTPD server", e)
            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service being destroyed")

        nanoHttpdServer?.stop()
        Log.d(TAG, "NanoHTTPD server stopped.")

        serviceScope.launch {
            aiMessageProcessor.shutdown()
        }

        serviceJob.cancel() // Cancel all coroutines started in this scope
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "AI Service"
            val descriptionText = "Background service for AI processing"
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("AI Service Running")
            .setContentText("Processing AI requests in background (NanoHTTPD)")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }
}