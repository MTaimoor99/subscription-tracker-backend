package com.example.subscription_tracker_backend.auth.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {
    @PostMapping("/auth/register")
    public Map<String,Object> sendRegistrationEmailToUser(@RequestBody Map<String,Object> body){
        Map<String,Object> response = new HashMap<String,Object>();
        //Response formatting after all is said and done.
        response.put("status","");
        response.put("data",new HashMap<String,Object>());
        response.put("message","");
        return response;
    }
}
