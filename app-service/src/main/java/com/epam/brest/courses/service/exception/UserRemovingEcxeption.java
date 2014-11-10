package com.epam.brest.courses.service.exception;

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
