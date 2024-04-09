package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class NotFoundError extends ApplicationException{
    public NotFoundError() {
        super(
                "user-not-found",
                "User with the given parameters not found",
                HttpStatus.NOT_FOUND
        );
    }

    public NotFoundError(String code, String message) {
        super(
                code,
                message,
                HttpStatus.NOT_FOUND
        );
    }
}
