package com.springmvc.student_management_system.service;

import com.springmvc.student_management_system.dto.ChatResponseDto;

public interface ChatBotService {
    ChatResponseDto sendMessage(String message);
}
