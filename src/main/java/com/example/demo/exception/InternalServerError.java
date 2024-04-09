package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class InternalServerError extends ApplicationException {
    public InternalServerError() {
        super(
                "internal-server-error",
                "An internal server error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    public InternalServerError(String code, String message) {
        super(
                code,
                message,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    public InternalServerError(Exception e) {
        super(
                "internal-server-error",
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
