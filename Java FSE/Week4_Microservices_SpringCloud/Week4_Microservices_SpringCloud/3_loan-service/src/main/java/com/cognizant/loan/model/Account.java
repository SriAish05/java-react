package com.cognizant.loan.model;

/**
 * Account model - mirror of the Account in account-service.
 * Feign uses this to deserialize the response from account-service.
 */
public class Account {
    private String number;
    private String type;
    private double balance;

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
