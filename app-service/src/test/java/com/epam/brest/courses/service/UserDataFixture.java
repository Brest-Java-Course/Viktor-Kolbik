package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataFixture {

    private static final String SOME_LOGIN = "some login";
    private static final String SOME_NAME = "some name";
    private static final String EMPTY_STRING = "";
    private static final String ADMIN_LOGIN = "admin";
    private static final Long SOME_ID = 3L;

    public static User getNullUser(){
        return null;
    }
    public static User getEmptyUser(){
        return new User();
    }
    public static User getCorrectUserById(Long userId){
        return new User(userId, SOME_LOGIN, SOME_NAME);
    }
    public static User getCorrectUserByLogin(String login){return new User(SOME_ID, login, SOME_NAME);}
    public static User getUserWithNullUserId(){
        return new User(null, SOME_LOGIN, SOME_NAME);
    }
    public static User getCorrectUserToUpdateAdmin(){
        return new User(0L, ADMIN_LOGIN, SOME_NAME);
    }
    public static User getUserWithEmptyName(){
        return new User(null, SOME_LOGIN, EMPTY_STRING);
    }
    public static User getUserWithNullName(){
        return new User(null, SOME_LOGIN, null);
    }
    public static User getUserWithNullLogin(){
        return new User(null, null, SOME_NAME);
    }
    public static User getUserWithEmptyLogin(){
        return new User(null, EMPTY_STRING, SOME_NAME);
    }


    public static List<User> getCorrectUsers(){
        List<User> users = new ArrayList<User>();

        users.add(new User(SOME_ID, SOME_LOGIN, SOME_NAME));
        users.add(new User(SOME_ID, SOME_LOGIN, SOME_NAME));
        users.add(new User(SOME_ID, SOME_LOGIN, SOME_NAME));

        return users;
    }
}
