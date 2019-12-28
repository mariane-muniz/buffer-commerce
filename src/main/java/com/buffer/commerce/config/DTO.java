package com.buffer.commerce.config;

public class DTO {
    private String message;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public DTO setStatus(Status status) {
        this.status = status;
        return this;
    }

    public DTO set(final String message, final Status status) {
        this.message = message;
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}