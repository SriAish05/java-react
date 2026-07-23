package com.cognizant.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka Discovery Server - the SERVICE REGISTRY.
 * 
 * @EnableEurekaServer turns this app into a registry where all other
 * microservices (account-service, loan-service) register themselves.
 * 
 * Think of it as a PHONE BOOK: services register their name + address,
 * and other services look them up by name instead of hardcoding URLs.
 * 
 * Dashboard: http://localhost:8761
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaDiscoveryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaDiscoveryServerApplication.class, args);
    }
}
