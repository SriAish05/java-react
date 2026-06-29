package com.singleton;

/**
 * Exercise 1: Singleton Pattern — Main Demo
 */
public class SingletonMain {

    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Demo ===\n");

        // First call — creates the instance
        Logger logger1 = Logger.getInstance();
        logger1.log("INFO", "Application started.");
        logger1.log("DEBUG", "Loading configuration.");

        // Second call — returns the SAME instance
        Logger logger2 = Logger.getInstance();
        logger2.log("WARN", "Low memory warning.");
        logger2.log("ERROR", "Connection timeout.");

        // Verify both references point to the same object
        System.out.println("\n--- Verification ---");
        System.out.println("logger1 == logger2: " + (logger1 == logger2));
        System.out.println("Total logs recorded: " + logger1.getLogCount());
        System.out.println("hashCode logger1: " + System.identityHashCode(logger1));
        System.out.println("hashCode logger2: " + System.identityHashCode(logger2));
    }
}
