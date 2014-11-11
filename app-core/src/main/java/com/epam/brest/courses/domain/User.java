package com.epam.brest.courses.domain;

/**
 * POJO object that presents a simple user.
 * @author Kolbik Viktor
 */
public class User {
    private Long userId;
    private String login;
    private String userName;

    /** Default Constructor.
     *  The default behaviour of this object is
     * All fields are null

     */
    public User(){
    }

    /** Constructor with two parameters.
     *  id has the default value(null).
     *  @param login User's login.
     *  @param userName User's name.
     */
    public User(String login, String userName){
        this.login = login;
        this.userName = userName;
    }

    /** Constructor with two parameters.
     *  @param userId User's id.
     *  @param login User's login.
     *  @param userName User's name.
     */
    public User(Long userId, String login, String userName) {
        this.userId = userId;
        this.login = login;
        this.userName = userName;
    }

    /**
     * gets User's id
     * @return User's id.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * sets User's id
     * @param userId User's id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }


    /**
     * gets User's login
     * @return User's login
     */
    public String getLogin() {
        return login;
    }

    /**
     * sets User's login
     * @param login User's login
     */
    public void setLogin(String login) {
        this.login = login;
    }


    /**
     * gets User's name
     * @return User's name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * sets User's name
     * @param userName User's name
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }
}
