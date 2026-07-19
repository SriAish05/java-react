package com.cognizant.springlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application for Hands-on 3: Advanced REST features.
 * 
 * Endpoints:
 *   GET    /api/countries         -> Get all
 *   GET    /api/countries/{code}  -> Get one (with HATEOAS links)
 *   GET    /api/countries/dto     -> Get all as DTOs
 *   GET    /api/countries/search?name=xxx -> Search by name
 *   POST   /api/countries         -> Create (with validation)
 *   PUT    /api/countries/{code}  -> Update
 *   DELETE /api/countries/{code}  -> Delete
 *   GET    /actuator/health       -> Health check
 *   GET    /actuator/info         -> App info
 *   GET    /actuator/metrics      -> Metrics
 */
@SpringBootApplication
public class SpringRestAdvancedApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringRestAdvancedApplication.class, args);
    }
}
