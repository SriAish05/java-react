package com.cognizant.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Account Service - a microservice that manages account data.
 * @EnableDiscoveryClient is auto-applied in Spring Cloud 2023+ when
 * the eureka-client dependency is on the classpath, so this service
 * automatically registers itself with the Eureka server on startup.
 */
@SpringBootApplication
public class AccountServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
}
