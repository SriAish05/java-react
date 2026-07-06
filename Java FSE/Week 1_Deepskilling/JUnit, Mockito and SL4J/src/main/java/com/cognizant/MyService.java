package com.cognizant;

/**
 * MyService — depends on ExternalApi.
 * Used to demonstrate Mockito mocking and stubbing.
 */
public class MyService {

    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public void notifyUser(String message) {
        externalApi.sendNotification(message);
    }

    public String processInput(String input) {
        return externalApi.processRequest(input);
    }
}
