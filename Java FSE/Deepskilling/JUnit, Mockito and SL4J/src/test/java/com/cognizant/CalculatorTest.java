package com.cognizant;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Exercise 1: Setting Up JUnit
 * Verifies basic JUnit 5 setup with simple Calculator tests.
 */
class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void testAdd() {
        int result = calculator.add(3, 7);
        assertEquals(10, result, "3 + 7 should equal 10");
    }

    @Test
    void testSubtract() {
        int result = calculator.subtract(10, 4);
        assertEquals(6, result, "10 - 4 should equal 6");
    }

    @Test
    void testMultiply() {
        int result = calculator.multiply(5, 6);
        assertEquals(30, result, "5 * 6 should equal 30");
    }

    @Test
    void testDivide() {
        double result = calculator.divide(15, 3);
        assertEquals(5.0, result, 0.001, "15 / 3 should equal 5.0");
    }

    @Test
    void testDivideByZeroThrowsException() {
        assertThrows(ArithmeticException.class,
                () -> calculator.divide(10, 0),
                "Division by zero should throw ArithmeticException");
    }
}
