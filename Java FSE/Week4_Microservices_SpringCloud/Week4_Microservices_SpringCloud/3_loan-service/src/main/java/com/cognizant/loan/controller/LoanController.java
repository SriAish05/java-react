package com.cognizant.loan.controller;

import com.cognizant.loan.model.Loan;
import com.cognizant.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Loan operations.
 */
@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    /**
     * GET /loans/{number} -> returns loan + linked account details.
     * The account part comes from account-service via Feign.
     */
    @GetMapping("/loans/{number}")
    public Loan getLoan(@PathVariable String number) {
        return loanService.getLoanDetails(number);
    }
}
