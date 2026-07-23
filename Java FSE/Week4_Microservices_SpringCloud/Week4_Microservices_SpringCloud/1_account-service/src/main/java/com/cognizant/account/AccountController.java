package com.cognizant.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller for Account Service.
 * Endpoint: GET /accounts/{number}
 */
@RestController
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    // In-memory sample data
    private static final Map<String, Account> ACCOUNTS = new HashMap<>();
    static {
        ACCOUNTS.put("1001", new Account("1001", "SAVINGS", 50000.0));
        ACCOUNTS.put("1002", new Account("1002", "CURRENT", 120000.0));
        ACCOUNTS.put("1003", new Account("1003", "SAVINGS", 75000.0));
    }

    @GetMapping("/accounts/{number}")
    public Account getAccount(@PathVariable String number) {
        LOGGER.info("Fetching account: {}", number);
        return ACCOUNTS.getOrDefault(number,
                new Account(number, "UNKNOWN", 0.0));
    }
}
