package com.hexaware.simplyfly.restcontrollers;

/**
 * DTO for user login request containing username and password.
 * 
 * Author: Vikashini
 * Version: 1.0
 */
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Password is required")
        String password
) {}
