package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class BadRequestError extends ApplicationException{
    public BadRequestError(String code, String message) {
        super(
                code,
                message,
                HttpStatus.NOT_FOUND
        );
    }
}
