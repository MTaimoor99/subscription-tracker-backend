package com.example.subscription_tracker_backend.auth.controller;
import com.example.subscription_tracker_backend.auth.service.AuthenticationService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Component
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService injectedAuthenticationService){
        authenticationService = injectedAuthenticationService;
    }


    @PostMapping("/auth/register")
    public Map<String,Object> sendRegistrationEmailToUser(@RequestBody Map<String,Object> body){

        Map<String,Object> response = authenticationService.sendRegistrationEmailToUser(body);

        return response;
    }
}
