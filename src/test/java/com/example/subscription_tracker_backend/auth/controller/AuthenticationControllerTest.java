package com.example.subscription_tracker_backend.auth.controller;

import com.example.subscription_tracker_backend.auth.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc; //simulate HTTP requests without running a server.

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuthenticationService authenticationService;

    @Test
    void validEmailAndValidPassword() throws Exception{
        // Arrange - Mock the service response
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("status", "success");
        mockResponse.put("message", "Registration successful");
        mockResponse.put("data", new HashMap<>());

        // Mock for Map parameter instead of individual strings
        when(authenticationService.sendRegistrationEmailToUser(any(Map.class)))
                .thenReturn(mockResponse);

        Map<String,Object> payload = new HashMap<String,Object>();
        payload.put("email","mt9sep2002@gmail.com");
        payload.put("password","123123");
        payload.put("confirmPassword","123123");
        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.message").exists()
                );
    }

    @Test
    void validEmailAndValidPasswordLengthGreaterThanSix() throws Exception{
        // Arrange - Mock the service response
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("status", "success");
        mockResponse.put("message", "Registration successful");
        mockResponse.put("data", new HashMap<>());

        // Mock for Map parameter instead of individual strings
        when(authenticationService.sendRegistrationEmailToUser(any(Map.class)))
                .thenReturn(mockResponse);


        Map<String,Object> payload = new HashMap<String,Object>();
        payload.put("email","mt9sep2002@gmail.com");
        payload.put("password","1231231");
        payload.put("confirmPassword","1231231");
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.message").exists()
                );
    }

    @Test
    void validEmailAndInvalidPassword() throws Exception{
        // Arrange - Mock the service response
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("status", "failure");
        mockResponse.put("message", "Password length less than 6 characters");
        mockResponse.put("data", new HashMap<>());

        // Mock for Map parameter instead of individual strings
        when(authenticationService.sendRegistrationEmailToUser(any(Map.class)))
                .thenReturn(mockResponse);

        Map<String,Object> payload = new HashMap<String,Object>();
        payload.put("email","mt9sep2002@gmail.com");
        payload.put("password","12312");
        payload.put("confirmPassword","12312");
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("failure"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void invalidEmailAndValidPassword() throws Exception{
        // Arrange - Mock the service response
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("status", "failure");
        mockResponse.put("message", "Invalid email address");
        mockResponse.put("data", new HashMap<>());

        // Mock for Map parameter instead of individual strings
        when(authenticationService.sendRegistrationEmailToUser(any(Map.class)))
                .thenReturn(mockResponse);

        Map<String,Object> payload = new HashMap<String,Object>();
        payload.put("email","mt9sep2002");
        payload.put("password","123123");
        payload.put("confirmPassword","123123");
        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("failure"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void invalidEmailAndPasswordsNotMatching() throws Exception{
        // Arrange - Mock the service response
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("status", "failure");
        mockResponse.put("message", "Password and confirm password field do not match");
        mockResponse.put("data", new HashMap<>());

        // Mock for Map parameter instead of individual strings
        when(authenticationService.sendRegistrationEmailToUser(any(Map.class)))
                .thenReturn(mockResponse);

        Map<String,Object> payload = new HashMap<String,Object>();
        payload.put("email","mt9sep2002@gmail.com");
        payload.put("password","123123");
        payload.put("confirmPassword","12312");
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("failure"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void emptyEmail() throws Exception{
        // Arrange - Mock the service response
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("status", "failure");
        mockResponse.put("message", "Email field cannot be empty");
        mockResponse.put("data", new HashMap<>());

        // Mock for Map parameter instead of individual strings
        when(authenticationService.sendRegistrationEmailToUser(any(Map.class)))
                .thenReturn(mockResponse);

        Map<String,Object> payload = new HashMap<String,Object>();
        payload.put("email","");
        payload.put("password","123123");
        payload.put("confirmPassword","123123");
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("failure"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void nullEmail() throws Exception{
        // Arrange - Mock the service response
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("status", "failure");
        mockResponse.put("message", "Email field cannot be null");
        mockResponse.put("data", new HashMap<>());

        // Mock for Map parameter instead of individual strings
        when(authenticationService.sendRegistrationEmailToUser(any(Map.class)))
                .thenReturn(mockResponse);

        Map<String,Object> payload = new HashMap<String,Object>();
        payload.put("email",null);
        payload.put("password","123123");
        payload.put("confirmPassword","123123");
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("failure"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void nullPassword() throws Exception{
        // Arrange - Mock the service response
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("status", "failure");
        mockResponse.put("message", "Password field cannot be null");
        mockResponse.put("data", new HashMap<>());

        // Mock for Map parameter instead of individual strings
        when(authenticationService.sendRegistrationEmailToUser(any(Map.class)))
                .thenReturn(mockResponse);

        Map<String,Object> payload = new HashMap<String,Object>();
        payload.put("email","mt9sep2002@gmail.com");
        payload.put("password",null);
        payload.put("confirmPassword","123123");
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("failure"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void emptyPassword() throws Exception{
        // Arrange - Mock the service response
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("status", "failure");
        mockResponse.put("message", "Password field cannot be empty");
        mockResponse.put("data", new HashMap<>());

        // Mock for Map parameter instead of individual strings
        when(authenticationService.sendRegistrationEmailToUser(any(Map.class)))
                .thenReturn(mockResponse);

        Map<String,Object> payload = new HashMap<String,Object>();
        payload.put("email","mt9sep2002@gmail.com");
        payload.put("password","");
        payload.put("confirmPassword","123123");
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("failure"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.message").exists());
    }


}
