package com.hexaware.simplyfly.restcontrollers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

public record RegistrationRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Please provide a valid email address")
        String email,
        @NotBlank(message = "Password is required")
        String password,
        @NotBlank(message = "Role is required")
        String role
) {
    public String username() {
        return email;
    }
}
