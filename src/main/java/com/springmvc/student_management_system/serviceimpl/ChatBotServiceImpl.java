package com.springmvc.student_management_system.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.springmvc.student_management_system.configuration.GeminiConfig;
import com.springmvc.student_management_system.dto.ChatResponseDto;
import com.springmvc.student_management_system.service.ChatBotService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatBotServiceImpl implements ChatBotService {

    @Autowired
    private GeminiConfig geminiConfig;

    private final RestTemplate restTemplate = new RestTemplate();
    private final Gson gson = new Gson();

    @Override
    public ChatResponseDto sendMessage(String message) {
        try {
            // Validate API key
            if (geminiConfig.getApi().getKey() == null || geminiConfig.getApi().getKey().isEmpty()) {
                log.error("Gemini API key is not configured");
                return ChatResponseDto.error("API key is not configured. Please add your Gemini API key to application.properties");
            }

            // Create a system instruction for the chatbot
            String systemInstruction = "You are a helpful AI assistant for a Student Management System. " +
                    "You help students, teachers, and administrators with questions about managing students, " +
                    "course registration, grades, and general academic information. " +
                    "Be concise, friendly, and professional in your responses.";

            // Combine system instruction with user message
            String fullPrompt = systemInstruction + "\n\nUser question: " + message;

            // Build the API URL
            String apiUrl = String.format(
                "https://generativelanguage.googleapis.com/v1/models/%s:generateContent?key=%s",
                geminiConfig.getModel(),
                geminiConfig.getApi().getKey()
            );

            log.info("Calling Gemini API with model: {}", geminiConfig.getModel());

            // Create request body
            JsonObject requestBody = new JsonObject();
            JsonArray contents = new JsonArray();
            JsonObject content = new JsonObject();
            JsonArray parts = new JsonArray();
            JsonObject part = new JsonObject();
            
            part.addProperty("text", fullPrompt);
            parts.add(part);
            content.add("parts", parts);
            contents.add(content);
            requestBody.add("contents", contents);

            log.debug("Request body: {}", requestBody.toString());

            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create HTTP entity
            HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

            // Make API call
            ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                entity,
                String.class
            );

            log.debug("Response status: {}", response.getStatusCode());
            log.debug("Response body: {}", response.getBody());

            // Parse response
            JsonObject responseJson = gson.fromJson(response.getBody(), JsonObject.class);
            
            // Check if response has error
            if (responseJson.has("error")) {
                JsonObject error = responseJson.getAsJsonObject("error");
                String errorMessage = error.get("message").getAsString();
                log.error("Gemini API error: {}", errorMessage);
                return ChatResponseDto.error("API Error: " + errorMessage);
            }

            String botResponse = responseJson
                .getAsJsonArray("candidates")
                .get(0).getAsJsonObject()
                .getAsJsonObject("content")
                .getAsJsonArray("parts")
                .get(0).getAsJsonObject()
                .get("text").getAsString();

            log.info("Gemini response generated successfully for message: {}", message);
            return new ChatResponseDto(botResponse);

        } catch (Exception e) {
            log.error("Error calling Gemini API: {}", e.getMessage(), e);
            String errorMsg = e.getMessage() != null ? e.getMessage() : "Unknown error";
            return ChatResponseDto.error("Sorry, I'm having trouble processing your request. Error: " + errorMsg);
        }
    }
}
