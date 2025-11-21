package com.springmvc.student_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {
    private String message;
    private String sender; // "user" or "bot"
    private Long timestamp;
}
