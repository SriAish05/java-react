package com.cognizant.loan.service;

import com.cognizant.loan.client.AccountClient;
import com.cognizant.loan.model.Account;
import com.cognizant.loan.model.Loan;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * LoanService - business logic for loans.
 *
 * Demonstrates CIRCUIT BREAKER pattern: if account-service is down,
 * instead of failing, the fallback method returns a safe default.
 */
@Service
public class LoanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanService.class);

    @Autowired
    private AccountClient accountClient;

    /**
     * Gets loan details AND the linked account (via Feign call to account-service).
     *
     * @CircuitBreaker: if account-service call fails repeatedly, the circuit
     * "opens" and calls go straight to fallbackGetLoan() instead of hanging.
     */
    @CircuitBreaker(name = "accountService", fallbackMethod = "fallbackGetLoan")
    public Loan getLoanDetails(String number) {
        LOGGER.info("Start getLoanDetails() for number: {}", number);

        Loan loan = new Loan(number, "car", 500000.0);

        // Inter-service call: fetch account details from account-service
        Account account = accountClient.getAccount(number);
        loan.setAccount(account);

        LOGGER.info("End getLoanDetails()");
        return loan;
    }

    /**
     * FALLBACK method - runs when account-service is unavailable.
     * Must have the same signature + a Throwable parameter.
     */
    public Loan fallbackGetLoan(String number, Throwable t) {
        LOGGER.warn("Fallback triggered for number: {}. Reason: {}", number, t.getMessage());
        Loan loan = new Loan(number, "car", 500000.0);
        Account fallbackAccount = new Account();
        fallbackAccount.setNumber(number);
        fallbackAccount.setType("UNAVAILABLE");
        fallbackAccount.setBalance(0.0);
        loan.setAccount(fallbackAccount);
        return loan;
    }
}
