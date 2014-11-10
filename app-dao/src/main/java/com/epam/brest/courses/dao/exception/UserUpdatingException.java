package com.epam.brest.courses.dao.exception;

import com.epam.brest.courses.domain.User;

/**
 * Created by simpson on 9.11.14.
 */
public class UserUpdatingException extends RootException {
    private User user;

    public UserUpdatingException(String message, User user){
        super(message);
        this.user = user;
    }

    public User getUser(){
        return user;
    }
}
