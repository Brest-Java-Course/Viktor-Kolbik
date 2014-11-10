package com.epam.brest.courses.service.exception;

/**
 * Created by simpson on 9.11.14.
 */
public class UsersNotFoundException extends RootException {

    public UsersNotFoundException(String message){
        super(message);
    }
}