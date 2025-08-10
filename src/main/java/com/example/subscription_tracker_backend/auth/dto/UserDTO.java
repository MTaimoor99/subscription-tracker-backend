package com.example.subscription_tracker_backend.auth.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserDTO {
    @Email(message = "Must be a valid email")
    @NotEmpty(message = "Email field must not be empty")
    @NotNull(message = "Email field must not be null")
    private String email;

    @NotEmpty(message = "Password field must not be empty")
    @NotNull(message = "Password field must not be null")
    @Min(value = 6, message = "Must be at least 6 characters in length")
    private String password;

    @NotEmpty(message = "Confirm password field must not be empty")
    @NotNull(message = "Confirm password field must not be null")
    @Min(value = 6, message = "Must be at least 6 characters in length")
    private String confirmPassword;
}
