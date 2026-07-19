package com.cognizant.jwt.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * CustomUserDetailsService loads users for authentication.
 * In production this would query a database. Here it uses an in-memory map.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, String> users = new HashMap<>();

    public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
        // Preload users with BCrypt-encoded passwords
        users.put("admin", passwordEncoder.encode("admin123"));
        users.put("user", passwordEncoder.encode("user123"));
        users.put("aish", passwordEncoder.encode("cognizant"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = users.get(username);
        if (password == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return User.withUsername(username)
                .password(password)
                .roles("USER")
                .build();
    }
}
