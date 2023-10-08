package org.example.utils.error;

public class CustomError extends RuntimeException {
    public CustomError(String message) {
        super(message);
    }
}
