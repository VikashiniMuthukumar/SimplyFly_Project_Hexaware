package com.hexaware.simplyfly.restcontrollers;

/**
 * REST controller for user authentication and registration.
 * Handles JWT token creation and user registration.
 * 
 * Author: Vikashini
 * Version: 1.0
 */

import com.hexaware.simplyfly.entities.User;
import com.hexaware.simplyfly.enums.UserRole;
import com.hexaware.simplyfly.security.JwtUtil;
import com.hexaware.simplyfly.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest) {
        if (customUserDetailsService.existsByEmail(registrationRequest.email())) {
            return ResponseEntity.badRequest().body(new AuthResponse("", "Email already exists"));
        }

        User user = new User();
        user.setEmail(registrationRequest.email());
        user.setPassword(passwordEncoder.encode(registrationRequest.password()));
        user.setRole(UserRole.valueOf(registrationRequest.role().toUpperCase()));
        customUserDetailsService.saveUser(user);

        return ResponseEntity.ok(new AuthResponse("", "User registered successfully"));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("", "Invalid credentials"));
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.username());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("USER");

        return ResponseEntity.ok(new AuthResponse(jwt, role));
    }

    @GetMapping("/test")
    public ResponseEntity<?> testProtectedEndpoint() {
        return ResponseEntity.ok(new AuthResponse("This is a protected endpoint", "USER"));
    }
}
