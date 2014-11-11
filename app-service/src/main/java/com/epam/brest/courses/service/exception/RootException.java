package com.epam.brest.courses.service.exception;

/**
 * Created to separate my exceptions from Other
 */
public class RootException extends RuntimeException {
    public RootException(String message){
        super(message);
    }
}
