package com.cognizant.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Loan Service - demonstrates INTER-SERVICE COMMUNICATION.
 * It calls the Account Service using a Feign client.
 * 
 * @EnableFeignClients activates declarative REST clients.
 */
@SpringBootApplication
@EnableFeignClients
public class LoanServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanServiceApplication.class, args);
    }
}
