package com.cognizant.loan;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Loan Controller - demonstrates calling another microservice.
 * 
 * The @CircuitBreaker annotation provides FAULT TOLERANCE:
 * if account-service is DOWN, instead of failing, it calls the
 * fallback method and returns a graceful default response.
 */
@RestController
public class LoanController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    private AccountClient accountClient;

    /**
     * GET /loans/{accountNumber}
     * Fetches account details from account-service and computes loan eligibility.
     */
    @GetMapping("/loans/{accountNumber}")
    @CircuitBreaker(name = "accountService", fallbackMethod = "getLoanFallback")
    public Map<String, Object> getLoanEligibility(@PathVariable String accountNumber) {
        LOGGER.info("Checking loan eligibility for account: {}", accountNumber);

        // Inter-service call via Feign
        Account account = accountClient.getAccount(accountNumber);

        // Simple loan logic: eligible for 5x balance
        double eligibleAmount = account.getBalance() * 5;

        Map<String, Object> response = new HashMap<>();
        response.put("accountNumber", account.getNumber());
        response.put("accountType", account.getType());
        response.put("balance", account.getBalance());
        response.put("loanEligibleAmount", eligibleAmount);
        response.put("status", "APPROVED");
        return response;
    }

    /**
     * Fallback method - called when account-service is unavailable.
     * Must have the SAME signature + a Throwable parameter.
     */
    public Map<String, Object> getLoanFallback(String accountNumber, Throwable t) {
        LOGGER.warn("Fallback triggered for account {}: {}", accountNumber, t.getMessage());
        Map<String, Object> response = new HashMap<>();
        response.put("accountNumber", accountNumber);
        response.put("status", "SERVICE_UNAVAILABLE");
        response.put("message", "Account service is currently down. Please try later.");
        return response;
    }
}
