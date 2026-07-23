package com.cognizant.loan.model;

/**
 * Loan model - represents a loan tied to an account.
 */
public class Loan {
    private String number;       // account number
    private String loanType;
    private double loanAmount;
    private Account account;     // linked account details (fetched via Feign)

    public Loan() {}

    public Loan(String number, String loanType, double loanAmount) {
        this.number = number;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
    }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public String getLoanType() { return loanType; }
    public void setLoanType(String loanType) { this.loanType = loanType; }
    public double getLoanAmount() { return loanAmount; }
    public void setLoanAmount(double loanAmount) { this.loanAmount = loanAmount; }
    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }
}
