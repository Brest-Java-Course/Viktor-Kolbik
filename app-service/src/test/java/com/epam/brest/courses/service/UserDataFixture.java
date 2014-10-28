package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataFixture {

    private static final String SOME_LOGIN = "some login";
    private static final String SOME_NAME = "some name";
    private static final String EMPTY_STRING = "";
    private static final Long SOME_ID = 3L;

    public static User getNullUser(){
        return null;
    }

    public static User getEmptyUser(){
        return new User();
    }

    public static User getUserWithNotNullUserId(Long userId){
        return new User(userId, SOME_LOGIN, SOME_NAME);
    }
    public static User getUserWithNullUserId(){
        return new User(null, SOME_LOGIN, SOME_NAME);
    }

    public static User getNewUserWithNullName(){
        return new User(null, SOME_LOGIN, null);
    }

    public static User getNewUserWithEmptyName(){
        return new User(null, SOME_LOGIN, EMPTY_STRING);
    }

    public static User getUserWithNullLogin(){
        return new User(null, null, SOME_NAME);
    }

    public static User getUserWithEmptyLogin(){
        return new User(null, EMPTY_STRING, SOME_NAME);
    }

    public static User getExistingUser(String login){
        return new User(null, login, SOME_NAME);
    }

    public static List<User> getExistingUsers(){
        List<User> users = new ArrayList<User>();

        users.add(new User(SOME_ID, SOME_LOGIN, SOME_NAME));
        users.add(new User(SOME_ID, SOME_LOGIN, SOME_NAME));
        users.add(new User(SOME_ID, SOME_LOGIN, SOME_NAME));

        return users;
    }

    public static User getExistingUserWithIdEqualsZero(){
        User user = new User(0L, "some name", "some login");

        return user;
    }

    public static User getExistingUserWithIdLessZero(){
        User user = new User(-1L, "some name", "some login");

        return user;
    }

    public static User getExistingUserById(Long id){
        User user = new User(id, "petrovich", "login");

        return user;
    }

    public static User getExistingUserByLogin(String login){
        User user = new User(3L, login, "some name");

        return user;
    }

    public static List<User> getExistingUsersByName(String usersName){
        List<User> users = new ArrayList<User>();

        users.add(new User(2L, "login1", usersName));
        users.add(new User(4L, "login2", usersName));
        users.add(new User(5L, "login3", usersName));

        return users;
    }
}
