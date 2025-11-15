package com.springmvc.student_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    
    @GetMapping("/")
    public String welcome() {
        return "home";
    }
    @GetMapping("/signin")
	public String login() {
		return "signin";
	}

}
