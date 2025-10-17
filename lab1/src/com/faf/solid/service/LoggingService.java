package com.faf.solid.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logging service - handles all logging operations
 * Single Responsibility: Only responsible for logging system events
 */
public class LoggingService {
    
    private static final DateTimeFormatter TIMESTAMP_FORMAT = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void logInfo(String message) {
        System.out.println("[INFO] " + getCurrentTimestamp() + " - " + message);
    }

    public void logWarning(String message) {
        System.out.println("[WARN] " + getCurrentTimestamp() + " - " + message);
    }

    public void logError(String message) {
        System.out.println("[ERROR] " + getCurrentTimestamp() + " - " + message);
    }

    public void logError(String message, Exception e) {
        System.out.println("[ERROR] " + getCurrentTimestamp() + " - " + message);
        System.out.println("Exception: " + e.getMessage());
    }

    public void logUserAction(String username, String action) {
        logInfo("User [" + username + "] performed action: " + action);
    }

    public void logSystemEvent(String event) {
        logInfo("System event: " + event);
    }

    public void logPerformanceMetric(String operation, long durationMs) {
        logInfo("Performance - " + operation + " completed in " + durationMs + "ms");
    }

    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(TIMESTAMP_FORMAT);
    }
}