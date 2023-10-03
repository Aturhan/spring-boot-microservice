package com.abdullahturhan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TaxiNotAvailableException extends RuntimeException {

    public TaxiNotAvailableException(String message) {
        super(message);
    }

    public TaxiNotAvailableException(String message,Throwable cause) {
        super(message, cause);
    }
}
