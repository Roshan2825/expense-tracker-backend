package com.roshan.expense_tracker.exception;

import java.util.List;

public class ErrorResponse {

    private boolean success;
    private String message;
    private List<String> errors;
    private long timestamp;

    public ErrorResponse(boolean success, String message, List<String> errors) {
        this.success = success;
        this.message = message;
        this.errors = errors;
        this.timestamp = System.currentTimeMillis();
    }

    public boolean isSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }
    public List<String> getErrors() {
        return errors;
    }
    public long getTimestamp() {
        return timestamp;
    }
}

