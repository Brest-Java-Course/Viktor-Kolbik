package com.epam.brest.courses.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    private static final Long USER_ID = 1L;
    private static final String USER_NAME = "user name";
    private static final String USER_LOGIN = "user login";

    private User user;

    @Before
    public void setUp() throws Exception{
        user = new User();
    }

    @Test
    public void testConstructorWithParameters() throws Exception{
        User user1 = new User(USER_ID, USER_LOGIN, USER_NAME);
        assertEquals(USER_ID, user1.getUserId());
        assertEquals(USER_LOGIN, user1.getLogin());
        assertEquals(USER_NAME, user1.getUserName());
    }

    @Test
    public void testGetName() throws Exception{
        user.setUserName(USER_NAME);
        assertEquals(USER_NAME, user.getUserName());
    }

    @Test
    public void testGetUserId() throws Exception{
        user.setUserId(USER_ID);
        assertEquals(USER_ID, user.getUserId());
    }

    @Test
    public void testGetLogin() throws Exception{
        user.setLogin(USER_LOGIN);
        assertEquals(USER_LOGIN, user.getLogin());
    }
}
