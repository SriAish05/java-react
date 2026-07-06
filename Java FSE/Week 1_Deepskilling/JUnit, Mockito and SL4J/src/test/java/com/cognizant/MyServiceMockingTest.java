package com.cognizant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Mockito Exercise 1: Mocking and Stubbing
 *
 * Demonstrates:
 * - Creating mock objects
 * - Stubbing methods with when().thenReturn()
 * - Stubbing consecutive calls
 * - Stubbing to throw exceptions
 */
@ExtendWith(MockitoExtension.class)
class MyServiceMockingTest {

    @Mock
    private ExternalApi mockApi;

    private MyService myService;

    @BeforeEach
    void setUp() {
        myService = new MyService(mockApi);
    }

    @Test
    @DisplayName("Exercise 1: Stub getData() to return predefined value")
    void testFetchDataWithStub() {
        // ARRANGE — stub the external API
        when(mockApi.getData()).thenReturn("Mock Data from Stubbed API");

        // ACT
        String result = myService.fetchData();

        // ASSERT
        assertEquals("Mock Data from Stubbed API", result,
                "fetchData() should return the stubbed value.");
    }

    @Test
    @DisplayName("Exercise 1: Stub with multiple consecutive return values")
    void testMultipleReturnValues() {
        // ARRANGE — first call returns "First", second returns "Second"
        when(mockApi.getData())
                .thenReturn("First Response")
                .thenReturn("Second Response")
                .thenReturn("Third Response");

        // ACT + ASSERT
        assertEquals("First Response",  myService.fetchData());
        assertEquals("Second Response", myService.fetchData());
        assertEquals("Third Response",  myService.fetchData());
        assertEquals("Third Response",  myService.fetchData());
    }

    @Test
    @DisplayName("Exercise 1: Stub processRequest() with argument matching")
    void testProcessRequestWithArgumentMatcher() {
        // ARRANGE — stub with specific argument
        when(mockApi.processRequest("VALID_INPUT")).thenReturn("Processed: VALID_INPUT");
        when(mockApi.processRequest(anyString())).thenReturn("Processed: generic input");

        // ACT + ASSERT
        assertEquals("Processed: VALID_INPUT", myService.processInput("VALID_INPUT"));
        assertEquals("Processed: generic input", myService.processInput("ANYTHING_ELSE"));
    }

    @Test
    @DisplayName("Exercise 1: Stub void method to do nothing (default behavior)")
    void testVoidMethodHandling() {
        assertDoesNotThrow(() -> myService.notifyUser("Hello World"));

        doThrow(new RuntimeException("Notification service down"))
                .when(mockApi).sendNotification("FAIL");

        assertThrows(RuntimeException.class, () -> myService.notifyUser("FAIL"));
    }

    @Test
    @DisplayName("Exercise 1: Stub getData() to throw exception")
    void testFetchDataThrowsException() {
        // ARRANGE
        when(mockApi.getData()).thenThrow(new RuntimeException("API unavailable"));

        // ACT + ASSERT
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> myService.fetchData());
        assertEquals("API unavailable", ex.getMessage());
    }
}
