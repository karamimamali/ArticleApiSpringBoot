package com.example.demo.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionAdvice {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ExceptionDTO> handleException(ApplicationException exception) {

        return new ResponseEntity<>(
                exception.toExceptionDTO(),
                exception.getStatus()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleException(Exception exception) {
        var applicationException = new InternalServerError(exception);
        return new ResponseEntity<>(
                applicationException.toExceptionDTO(),
                applicationException.getStatus()
        );
    }
}
