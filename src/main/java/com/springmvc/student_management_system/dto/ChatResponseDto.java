package com.springmvc.student_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponseDto {
    private String response;
    private boolean success;
    private String error;
    
    public ChatResponseDto(String response) {
        this.response = response;
        this.success = true;
    }
    
    public static ChatResponseDto error(String error) {
        ChatResponseDto dto = new ChatResponseDto();
        dto.setError(error);
        dto.setSuccess(false);
        return dto;
    }
}
