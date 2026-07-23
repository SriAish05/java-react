package com.cognizant.account.controller;

import com.cognizant.account.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller for Account operations.
 */
@RestController
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    // In-memory sample data
    private final Map<String, Account> accounts = new HashMap<>();

    public AccountController() {
        accounts.put("178849", new Account("178849", "savings", 25000.0));
        accounts.put("178850", new Account("178850", "current", 150000.0));
        accounts.put("178851", new Account("178851", "savings", 8500.0));
    }

    /**
     * GET /accounts/{number} -> returns account details.
     */
    @GetMapping("/accounts/{number}")
    public Account getAccount(@PathVariable String number) {
        LOGGER.info("Start getAccount() for number: {}", number);
        Account account = accounts.getOrDefault(number,
                new Account(number, "unknown", 0.0));
        LOGGER.info("End getAccount()");
        return account;
    }
}
