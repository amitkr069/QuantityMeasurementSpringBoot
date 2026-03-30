package com.app.quantitymeasurement.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUnitException.class)
    public ResponseEntity<String> handleInvalidUnit(InvalidUnitException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}