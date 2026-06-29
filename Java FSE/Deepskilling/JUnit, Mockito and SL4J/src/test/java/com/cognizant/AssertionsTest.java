package com.cognizant;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Exercise 3: Assertions in JUnit
 * Demonstrates all common JUnit 5 assertion methods.
 */
class AssertionsTest {

    private final Calculator calculator = new Calculator();

    @Test
    void testAssertEquals() {
        assertEquals(5, calculator.add(2, 3), "2 + 3 must equal 5");
    }

    @Test
    void testAssertNotEquals() {
        assertNotEquals(10, calculator.add(2, 3), "2 + 3 must not equal 10");
    }

    @Test
    void testAssertTrue() {
        assertTrue(calculator.isEven(4), "4 should be even");
    }

    @Test
    void testAssertFalse() {
        assertFalse(calculator.isEven(7), "7 should not be even");
    }

    @Test
    void testAssertNull() {
        String value = null;
        assertNull(value, "Value should be null");
    }

    @Test
    void testAssertNotNull() {
        Calculator calc = new Calculator();
        assertNotNull(calc, "Calculator instance should not be null");
    }

    @Test
    void testAssertArrayEquals() {
        int[] expected = {1, 4, 9, 16, 25};
        int[] actual   = {1, 4, 9, 16, 25};
        assertArrayEquals(expected, actual, "Arrays must match");
    }

    @Test
    void testAssertThrows() {
        assertThrows(ArithmeticException.class,
                () -> calculator.divide(10, 0));
    }

    @Test
    void testAssertThrowsWithMessage() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.factorial(-1)
        );
        assertEquals("Factorial not defined for negative numbers.", ex.getMessage());
    }

    @Test
    void testAssertAll() {
        assertAll("Calculator operations",
                () -> assertEquals(5,  calculator.add(2, 3)),
                () -> assertEquals(1,  calculator.subtract(4, 3)),
                () -> assertEquals(12, calculator.multiply(3, 4)),
                () -> assertEquals(2.5, calculator.divide(5, 2), 0.001)
        );
    }
}
