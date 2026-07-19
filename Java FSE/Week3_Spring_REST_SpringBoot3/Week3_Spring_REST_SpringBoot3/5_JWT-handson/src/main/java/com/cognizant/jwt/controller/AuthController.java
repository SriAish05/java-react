package com.cognizant.jwt.controller;

import com.cognizant.jwt.model.AuthRequest;
import com.cognizant.jwt.model.AuthResponse;
import com.cognizant.jwt.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController - handles user login and JWT generation.
 * 
 * HANDS-ON: Create authentication service that returns JWT
 * 
 * Endpoint: POST /authenticate
 * Body: { "username": "aish", "password": "cognizant" }
 * Response: { "token": "eyJhbGc...", "username": "aish", "type": "Bearer" }
 */
@RestController
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {
        LOGGER.info("Authenticating user: {}", request.getUsername());
        try {
            // Step 1: Spring Security verifies credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            LOGGER.warn("Invalid credentials for user: {}", request.getUsername());
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        // Step 2: If credentials valid, generate JWT
        String token = jwtService.generateToken(request.getUsername());
        LOGGER.info("Token generated for user: {}", request.getUsername());
        return ResponseEntity.ok(new AuthResponse(token, request.getUsername()));
    }
}
