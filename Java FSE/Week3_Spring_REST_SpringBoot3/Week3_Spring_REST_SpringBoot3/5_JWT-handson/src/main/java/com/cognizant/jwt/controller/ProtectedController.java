package com.cognizant.jwt.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Protected endpoints - only accessible with a valid JWT.
 * Client must send: Authorization: Bearer <token>
 */
@RestController
public class ProtectedController {

    /**
     * GET /hello - returns greeting for authenticated user.
     */
    @GetMapping("/hello")
    public String hello(@AuthenticationPrincipal UserDetails user) {
        return "Hello, " + user.getUsername() + "! You are authenticated.";
    }

    /**
     * GET /profile - returns user profile info.
     */
    @GetMapping("/profile")
    public Map<String, Object> profile(@AuthenticationPrincipal UserDetails user) {
        Map<String, Object> profile = new HashMap<>();
        profile.put("username", user.getUsername());
        profile.put("authorities", user.getAuthorities());
        profile.put("message", "Access granted with valid JWT");
        return profile;
    }
}
