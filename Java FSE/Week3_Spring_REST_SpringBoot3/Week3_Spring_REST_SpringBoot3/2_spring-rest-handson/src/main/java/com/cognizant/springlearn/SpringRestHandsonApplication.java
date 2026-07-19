package com.cognizant.springlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application for Hands-on 2: RESTful Web Services with Spring Boot 3
 * 
 * Endpoints available after startup:
 *   http://localhost:8080/hello          -> Hello World
 *   http://localhost:8080/country        -> Single Country
 *   http://localhost:8080/countries      -> All Countries
 *   http://localhost:8080/country/IN     -> Get Country by code
 */
@SpringBootApplication
public class SpringRestHandsonApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringRestHandsonApplication.class, args);
    }
}
