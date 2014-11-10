package com.epam.brest.courses.service.exception;

/**
 * Created by simpson on 9.11.14.
 */
public class UsersForNameNotFoundException extends UsersNotFoundException {
    private String name;

    public UsersForNameNotFoundException(String message, String name){
        super(message);
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
