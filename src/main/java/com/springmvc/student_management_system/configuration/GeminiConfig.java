package com.springmvc.student_management_system.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "gemini")
public class GeminiConfig {
    
    private Api api = new Api();
    private String model = "gemini-1.5-flash";
    
    @Data
    public static class Api {
        private String key;
    }
}
