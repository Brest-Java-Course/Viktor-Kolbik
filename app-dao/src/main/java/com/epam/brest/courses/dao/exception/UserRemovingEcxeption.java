package com.epam.brest.courses.dao.exception;

import com.epam.brest.courses.domain.User;

public class UserRemovingEcxeption extends RootException {
    private Long userId;

    public UserRemovingEcxeption(String message, Long userId){
        super(message);
        this.userId = userId;
    }

    public Long getId(){
        return userId;
    }

}
