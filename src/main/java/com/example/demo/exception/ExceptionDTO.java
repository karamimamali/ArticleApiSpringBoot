package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public record ExceptionDTO(
        String code,
        String message,
        HttpStatus status
) {
    public ApplicationException toApplicationException(HttpStatus status) {
        return new ApplicationException(code, "Client Error", status);
    }
}
