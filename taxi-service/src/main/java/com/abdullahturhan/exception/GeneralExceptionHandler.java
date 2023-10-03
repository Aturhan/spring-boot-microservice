package com.abdullahturhan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(TaxiNotFoundException.class)
    public ResponseEntity<?> handleException(TaxiNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());

    }
    @ExceptionHandler(TaxiNotAvailableException.class)
    public ResponseEntity<?> handleException(TaxiNotAvailableException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
