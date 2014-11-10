package com.epam.brest.courses.dao.exception;

/**
 * Created by simpson on 9.11.14.
 */
public class UserForIdNotFoundException extends RootException {

    private Long id;

    public UserForIdNotFoundException(String message, Long id){
        super(message);
        this.id = id;
    }

    public Long getId(){
        return id;
    }
}
