package com.abdullahturhan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaxiNotFoundException extends RuntimeException{
    public TaxiNotFoundException(String message){
        super(message);
    }

    public TaxiNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
