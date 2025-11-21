package com.springmvc.student_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequestDto {
    @NotBlank(message = "Message cannot be empty")
    private String message;
}
