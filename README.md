# earthquake-damage-assessment-ollama
earthquake-damage-assessment-ollama

1. Make Sure Ollama is Running

First, confirm that your local Ollama server is actually running. The easiest way to check is to open a new terminal (not the one for your Java app) and run:
command prompt: curl http://localhost:11434

>> If it's working, you'll see a message like: Ollama is running.
>> If it's not working, you'll get an error like curl: (7) Failed to connect to localhost port 11434: Connection refused. If you get this error, you must start your Ollama application.

2. Manually Pull the llava Model

Your application is trying to use the llava model. The 5-minute timeout might be happening because your app is trying to download this large model during startup.
It's much better to pull it manually beforehand. In your terminal, run:
command prompt: ollama pull llava

Wait for this to complete. It may take several minutes as it's a large file.

3. Launch Your Spring Boot Application

Once you've confirmed Ollama is running (Step 1) and you have manually pulled the llava model (Step 2), try running your Spring Boot application.
It should now be able to connect instantly, find the model, and start successfully.

*** IDENTIFY THE RIGHT MODEL FOR YOUR EARTHQUAKE REQUIREMENT ***
