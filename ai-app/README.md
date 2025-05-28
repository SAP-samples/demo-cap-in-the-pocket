# MediaPipe Android App

## Overview
This project is an Android application that utilizes MediaPipe for generative AI processing. It downloads a model from Hugging Face and runs a background service to handle incoming AI message requests via an HTTP server.

## Features
- Downloads a generative AI model from a specified URL.
- Displays a simple user interface with the text "AI code for CAP demo".
- Runs a background service to process incoming requests.
- Implements an HTTP server to listen for requests on a specified port.

## Setup Instructions
1. **Clone the Repository**
   ```
   git clone <repository-url>
   cd mediapipe-android-app
   ```

2. **Open the Project**
   Open the project in your preferred IDE (e.g., Android Studio).

3. **Build the Project**
   Ensure that all dependencies are resolved and build the project.

4. **Run the Application**
   Connect an Android device or start an emulator, then run the application.

## Usage
Once the application is running, it will display a blank page with the text "AI code for CAP demo". The background service will be active, ready to receive generative AI message requests.

## Querying the LLM via HTTP
The application runs an HTTP server on port 8087 that accepts AI message requests. You can use curl to interact with the model:

### Basic Query
```bash
curl -X POST "http://localhost:8087/generate" \
  -H "Content-Type: application/json" \
  -d '{"prompt": "What is the capital of France?"}'
```

Parameters:
- `prompt` (required): The input text to send to the LLM

## License
This project is licensed under the MIT License. See the LICENSE file for more details.