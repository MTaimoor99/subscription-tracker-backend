package com.example.subscription_tracker_backend.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Authentication {
    @GetMapping("/register")
    public Map<String,Object> registration(String email, String password){
        Map<String,Object> response = new HashMap<>();
        return response;
    }
}
