package com.example.demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ApplicationException extends RuntimeException {
    private final String code;
    private final String message;
    private final HttpStatus status;

    public ExceptionDTO toExceptionDTO() {
        return new ExceptionDTO(code, message, status);
    }

    @Override
    public String toString() {
        return String.format("{ code: %s, message: %s, status: %d }", code, message, status.value());
    }
}
