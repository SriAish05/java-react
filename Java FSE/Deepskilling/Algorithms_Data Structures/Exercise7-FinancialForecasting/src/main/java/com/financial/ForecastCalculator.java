package com.financial;

import java.util.HashMap;
import java.util.Map;

/**
 * Exercise 7: Financial Forecasting using Recursion
 *
 * Formula: FutureValue = PresentValue * (1 + growthRate)^years
 *
 * Time Complexity:
 * - Naive recursion: O(n) — one call per year
 * - Memoized recursion: O(n) first call, O(1) subsequent calls for same year
 *
 * Space Complexity: O(n) for the call stack / memo map
 */
public class ForecastCalculator {

    // Memoization cache: year -> future value
    private static final Map<Integer, Double> memo = new HashMap<>();

    /**
     * Naive recursive calculation of future value.
     * FV(n) = FV(n-1) * (1 + rate)
     */
    public static double calculateFutureValueRecursive(double presentValue,
                                                        double annualGrowthRate,
                                                        int years) {
        // Base case
        if (years == 0) {
            return presentValue;
        }
        // Recursive step
        return calculateFutureValueRecursive(presentValue, annualGrowthRate, years - 1)
               * (1 + annualGrowthRate);
    }

    /**
     * Memoized recursive calculation — avoids redundant computations.
     */
    public static double calculateFutureValueMemoized(double presentValue,
                                                       double annualGrowthRate,
                                                       int years) {
        if (years == 0) {
            return presentValue;
        }
        if (memo.containsKey(years)) {
            return memo.get(years);
        }
        double result = calculateFutureValueMemoized(presentValue, annualGrowthRate, years - 1)
                        * (1 + annualGrowthRate);
        memo.put(years, result);
        return result;
    }

    public static void main(String[] args) {
        double presentValue   = 10_000.0;   // Initial investment
        double annualGrowthRate = 0.08;     // 8% annual growth
        int    forecastYears  = 10;

        System.out.println("=== Financial Forecasting (Recursive) ===");
        System.out.printf("Present Value     : $%,.2f%n", presentValue);
        System.out.printf("Annual Growth Rate: %.0f%%%n", annualGrowthRate * 100);
        System.out.printf("Forecast Period   : %d years%n", forecastYears);
        System.out.println("─".repeat(45));
        System.out.printf("%-10s %-20s %-20s%n", "Year", "Recursive FV", "Memoized FV");
        System.out.println("─".repeat(45));

        for (int year = 1; year <= forecastYears; year++) {
            double fvRecursive  = calculateFutureValueRecursive(presentValue, annualGrowthRate, year);
            double fvMemoized   = calculateFutureValueMemoized(presentValue, annualGrowthRate, year);
            System.out.printf("%-10d $%-19.2f $%-19.2f%n", year, fvRecursive, fvMemoized);
        }

        System.out.println("─".repeat(45));
        double finalValue = calculateFutureValueRecursive(presentValue, annualGrowthRate, forecastYears);
        System.out.printf("Final FV after %d years: $%,.2f%n", forecastYears, finalValue);
        System.out.printf("Net Growth: $%,.2f (%.1f%% total return)%n",
                finalValue - presentValue,
                ((finalValue - presentValue) / presentValue) * 100);
    }
}
