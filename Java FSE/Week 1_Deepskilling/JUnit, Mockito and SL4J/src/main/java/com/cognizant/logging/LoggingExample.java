package com.cognizant.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SLF4J Exercise 1: Logging Error Messages and Warning Levels
 *
 * SLF4J Log levels (low to high):
 * TRACE → DEBUG → INFO → WARN → ERROR
 *
 * Uses Logback as the underlying implementation (configured in logback.xml).
 */
public class LoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        System.out.println("=== SLF4J Logging Demo ===\n");

        // TRACE - most granular, for detailed diagnostics
        logger.trace("TRACE: Entering main method.");

        // DEBUG - development/debugging info
        logger.debug("DEBUG: Application initialized with default configuration.");

        // INFO - general operational events
        logger.info("INFO: Application started successfully.");
        logger.info("INFO: Processing {} records.", 42);

        // WARN - unexpected but recoverable situations
        logger.warn("WARN: Memory usage is at 80% capacity.");
        logger.warn("WARN: Retry attempt {} of {} for service call.", 2, 3);

        // ERROR - serious failures
        logger.error("ERROR: Failed to connect to database.");
        logger.error("ERROR: Null pointer encountered in module: {}", "UserService");

        // Logging with exception
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.error("ERROR: Arithmetic exception caught - {}", e.getMessage(), e);
        }

        logger.info("INFO: Application shutdown initiated.");
        System.out.println("\nCheck app.log for file-appender output.");
    }
}
