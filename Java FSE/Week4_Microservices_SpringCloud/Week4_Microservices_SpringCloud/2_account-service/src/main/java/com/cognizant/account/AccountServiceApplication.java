package com.cognizant.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ACCOUNT SERVICE - a microservice that manages bank accounts.
 *
 * On startup, it automatically registers itself with the Eureka server
 * (because spring-cloud-starter-netflix-eureka-client is on the classpath).
 *
 * Endpoint: GET /accounts/{number}
 */
@SpringBootApplication
public class AccountServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
}
