package com.springmvc.student_management_system.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springmvc.student_management_system.configuration.GeminiConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/chatbot")
public class ChatBotTestController {

    @Autowired
    private GeminiConfig geminiConfig;

    @GetMapping("/config-test")
    public ResponseEntity<Map<String, String>> testConfig() {
        Map<String, String> config = new HashMap<>();
        
        String apiKey = geminiConfig.getApi().getKey();
        if (apiKey != null && !apiKey.isEmpty()) {
            config.put("apiKeyConfigured", "Yes");
            config.put("apiKeyLength", String.valueOf(apiKey.length()));
            config.put("apiKeyPreview", apiKey.substring(0, Math.min(10, apiKey.length())) + "...");
        } else {
            config.put("apiKeyConfigured", "No");
        }
        
        config.put("model", geminiConfig.getModel());
        config.put("apiUrl", String.format(
            "https://generativelanguage.googleapis.com/v1beta/models/%s:generateContent",
            geminiConfig.getModel()
        ));
        
        log.info("Configuration test result: {}", config);
        return ResponseEntity.ok(config);
    }
}
