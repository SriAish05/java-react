package com.cognizant;

/**
 * Simple Calculator — used as the class under test for JUnit exercises.
 */
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return (double) a / b;
    }

    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    public int factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Factorial not defined for negative numbers.");
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }
}
