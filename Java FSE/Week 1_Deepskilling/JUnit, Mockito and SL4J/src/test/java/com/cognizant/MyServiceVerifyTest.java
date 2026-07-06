package com.cognizant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Mockito Exercise 2: Verifying Interactions
 *
 * Demonstrates:
 * - verify() — method was called
 * - verify(mock, times(n)) — method called n times
 * - verify(mock, never()) — method never called
 * - verify(mock, atLeast/atMost) — boundary verification
 * - InOrder — verifying call sequence
 * - Argument matchers in verify
 */
@ExtendWith(MockitoExtension.class)
class MyServiceVerifyTest {

    @Mock
    private ExternalApi mockApi;

    private MyService myService;

    @BeforeEach
    void setUp() {
        myService = new MyService(mockApi);
    }

    @Test
    @DisplayName("Exercise 2: Verify getData() was called exactly once")
    void testVerifyGetDataCalledOnce() {
        when(mockApi.getData()).thenReturn("data");

        myService.fetchData();

        verify(mockApi).getData();
        verify(mockApi, times(1)).getData();
    }

    @Test
    @DisplayName("Exercise 2: Verify method called multiple times")
    void testVerifyCalledMultipleTimes() {
        when(mockApi.getData()).thenReturn("data");

        myService.fetchData();
        myService.fetchData();
        myService.fetchData();

        verify(mockApi, times(3)).getData();
        verify(mockApi, atLeast(2)).getData();
        verify(mockApi, atMost(5)).getData();
    }

    @Test
    @DisplayName("Exercise 2: Verify method was NEVER called")
    void testVerifyNeverCalled() {
        verify(mockApi, never()).getData();
        verify(mockApi, never()).sendNotification(anyString());
    }

    @Test
    @DisplayName("Exercise 2: Verify with specific argument")
    void testVerifyWithSpecificArgument() {
        myService.notifyUser("Payment confirmed");

        verify(mockApi).sendNotification("Payment confirmed");
        verify(mockApi, never()).sendNotification("Wrong message");
    }

    @Test
    @DisplayName("Exercise 2: Verify interaction ORDER using InOrder")
    void testVerifyInteractionOrder() {
        when(mockApi.getData()).thenReturn("order data");

        myService.fetchData();
        myService.notifyUser("Processing complete");

        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).sendNotification("Processing complete");
    }

    @Test
    @DisplayName("Exercise 2: Verify no more interactions after expected calls")
    void testVerifyNoMoreInteractions() {
        when(mockApi.getData()).thenReturn("data");

        myService.fetchData();

        verify(mockApi).getData();
        verifyNoMoreInteractions(mockApi);
    }
}
