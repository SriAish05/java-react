package com.cognizant;

/**
 * ExternalApi interface — mocked in Mockito exercises.
 * Represents a third-party API dependency.
 */
public interface ExternalApi {
    String getData();
    void sendNotification(String message);
    String processRequest(String input);
}
