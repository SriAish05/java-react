package com.singleton;

/**
 * Exercise 1: Implementing the Singleton Pattern
 * Scenario: A logger utility that must have only one instance throughout the application.
 * Uses double-checked locking for thread safety.
 */
public class Logger {

    // volatile ensures visibility across threads
    private static volatile Logger instance;
    private int logCount;

    // Private constructor prevents external instantiation
    private Logger() {
        this.logCount = 0;
        System.out.println("Logger instance created (only once).");
    }

    /**
     * Double-checked locking singleton — thread-safe, lazy-initialized.
     */
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String level, String message) {
        logCount++;
        System.out.printf("[%s] [Log #%d] %s%n", level, logCount, message);
    }

    public int getLogCount() {
        return logCount;
    }
}
