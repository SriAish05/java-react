package com.cognizant.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application for JWT Hands-on.
 * 
 * Endpoints:
 *   POST /authenticate  -> Login and get JWT (PUBLIC)
 *   GET  /hello         -> Protected (needs Authorization: Bearer <token>)
 *   GET  /profile       -> Protected
 * 
 * Default users:
 *   admin / admin123
 *   user  / user123
 *   aish  / cognizant
 */
@SpringBootApplication
public class JwtHandsonApplication {
    public static void main(String[] args) {
        SpringApplication.run(JwtHandsonApplication.class, args);
    }
}
