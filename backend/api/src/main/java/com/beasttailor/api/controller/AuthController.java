package com.beasttailor.api.controller;

import com.beasttailor.api.model.User;
import com.beasttailor.api.service.AuthService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth") 
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        try {
            User newUser = authService.registerUser(
                registrationRequest.getUsername(),
                registrationRequest.getPassword()
            );
            // On success, return a 201 Created status with a success message
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
        } catch (IllegalStateException e) {
            // If the username already exists, return a 409 Conflict status
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            // For any other unexpected errors, return a 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    /**
     * A simple DTO (Data Transfer Object) class to map the incoming JSON request body.
     */
    
    @Data
    private static class RegistrationRequest {
        private String username;
        private String password;
    }
}