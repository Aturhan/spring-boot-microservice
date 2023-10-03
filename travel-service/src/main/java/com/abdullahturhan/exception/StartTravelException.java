package com.abdullahturhan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StartTravelException extends RuntimeException{
    public StartTravelException(String message){
        super(message);
    }
    public StartTravelException(String message,Throwable cause){
        super(message, cause);
    }

}
