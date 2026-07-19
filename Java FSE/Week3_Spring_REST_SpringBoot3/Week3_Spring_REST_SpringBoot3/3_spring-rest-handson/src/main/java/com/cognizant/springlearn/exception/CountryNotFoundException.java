package com.cognizant.springlearn.exception;

/**
 * Custom exception for missing country.
 */
public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String message) {
        super(message);
    }
}
