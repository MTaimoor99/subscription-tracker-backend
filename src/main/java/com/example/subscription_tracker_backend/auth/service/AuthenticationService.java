package com.example.subscription_tracker_backend.auth.service;

import com.example.subscription_tracker_backend.common.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticationService {
    private EmailService emailService;

    @Autowired
    public AuthenticationService(EmailService injectedEmailService){
        emailService = injectedEmailService;
    }

    public Map<String,Object> sendRegistrationEmailToUser(){
        return new HashMap<String,Object>();
    }
}
