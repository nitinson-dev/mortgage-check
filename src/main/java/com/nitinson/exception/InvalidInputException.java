package com.nitinson.exception;

import org.springframework.http.HttpStatus;

public class InvalidInputException extends RuntimeException {
    private HttpStatus status = null;

    public InvalidInputException() {
        super();
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(HttpStatus status, String message) {
        this(message);
        this.status = status;
    }
}
