package com.cognizant.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * API GATEWAY - the single entry point for all client requests.
 *
 * Instead of clients calling account-service (8081) and loan-service (8082)
 * directly, they call the gateway (8080). The gateway routes each request to
 * the right service - looked up dynamically from Eureka.
 *
 * Benefits: one entry point, central place for auth, logging, rate-limiting,
 * and clients don't need to know individual service addresses.
 *
 * Routes (configured in application.yml):
 *   /accounts/**  -> account-service
 *   /loans/**     -> loan-service
 */
@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
