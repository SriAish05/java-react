package com.cognizant.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * LOAN SERVICE - a microservice that manages loans.
 *
 * This service demonstrates INTER-SERVICE COMMUNICATION: it calls the
 * Account Service using a Feign client to fetch account details.
 *
 * @EnableFeignClients activates Feign declarative REST clients.
 *
 * Endpoint: GET /loans/{number}
 */
@SpringBootApplication
@EnableFeignClients
public class LoanServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanServiceApplication.class, args);
    }
}
