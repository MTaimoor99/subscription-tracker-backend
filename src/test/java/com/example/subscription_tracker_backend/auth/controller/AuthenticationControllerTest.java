package com.example.subscription_tracker_backend.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc; //simulate HTTP requests without running a server.

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void validEmailAndValidPassword() throws Exception{
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
