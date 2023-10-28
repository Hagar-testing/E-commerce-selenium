package org.hager.utils;

public class CustomAutomationException extends RuntimeException {
    public CustomAutomationException(String message, Throwable cause) {
        super(message, cause);
    }
}