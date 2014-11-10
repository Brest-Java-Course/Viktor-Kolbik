package com.epam.brest.courses.service.exception;

public class UserForLoginNotFoundException extends RootException {
    private String login;

    public UserForLoginNotFoundException(String message, String login){
        super(message);

        this.login = login;
    }

    public String getLogin(){
        return login;
    }
}
