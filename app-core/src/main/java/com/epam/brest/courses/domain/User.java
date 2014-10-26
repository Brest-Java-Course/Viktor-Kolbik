package com.epam.brest.courses.domain;

public class User {
    private Long userId;
    private String login;
    private String userName;

    public User(){

    }

    public User(Long userId, String login, String userName){
        this.userId = userId;
        this.login = login;
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", name='" + userName + '\'' +
                '}';
    }

}
