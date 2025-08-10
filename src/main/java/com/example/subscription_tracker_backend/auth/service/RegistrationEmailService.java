package com.example.subscription_tracker_backend.auth.service;

import com.example.subscription_tracker_backend.common.EmailService;
import org.springframework.stereotype.Component;

@Component
public class RegistrationEmailService implements EmailService {
    @Override
    public void sendEmail(String email) {

    }
}
