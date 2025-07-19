package com.example.subscription_tracker_backend.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Authentication {
    @PostMapping("/auth/register")
    public Map<String,Object> registration(@RequestBody Map<String,String> body){
        Map<String,Object> response = new HashMap<>();

        return response;
    }
}
