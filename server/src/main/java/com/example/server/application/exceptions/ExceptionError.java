package com.example.server.application.exceptions;

import java.io.Serial;
import java.io.Serializable;

public record ExceptionError(String message, String statusMessage, Integer statusCode) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public ExceptionError(String message, String statusMessage, Integer statusCode) {
        this.message = message;
        this.statusMessage = statusMessage;
        this.statusCode = statusCode != null ? statusCode : 500;
    }
}
