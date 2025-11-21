package com.springmvc.student_management_system.controller;

import com.springmvc.student_management_system.dto.ChatRequestDto;
import com.springmvc.student_management_system.dto.ChatResponseDto;
import com.springmvc.student_management_system.service.ChatBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/chatbot")
public class ChatBotController {

    @Autowired
    private ChatBotService chatBotService;

    @PostMapping("/chat")
    public ResponseEntity<ChatResponseDto> chat(@Valid @RequestBody ChatRequestDto request) {
        log.info("Received chat request: {}", request.getMessage());
        
        ChatResponseDto response = chatBotService.sendMessage(request.getMessage());
        
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Chatbot service is running");
    }
}
