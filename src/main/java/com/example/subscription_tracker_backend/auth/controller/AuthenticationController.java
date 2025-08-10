package com.example.subscription_tracker_backend.auth.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class AuthenticationController {
    @PostMapping("/auth/register")
    public Map<String,Object> registration(@RequestBody Map<String,Object> body){
        return body;
    }
}
