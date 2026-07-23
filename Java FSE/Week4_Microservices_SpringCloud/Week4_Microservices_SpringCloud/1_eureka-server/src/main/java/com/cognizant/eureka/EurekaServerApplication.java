package com.cognizant.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * EUREKA SERVER - The Service Registry.
 *
 * This is the "phone book" of the microservices system. Every other service
 * registers itself here on startup. When one service wants to call another,
 * it asks Eureka "where is account-service?" instead of hardcoding an address.
 *
 * @EnableEurekaServer turns this Spring Boot app into a Eureka registry.
 *
 * Dashboard: http://localhost:8761
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
