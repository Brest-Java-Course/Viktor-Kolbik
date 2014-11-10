package com.epam.brest.courses.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    private static final Long USER_ID = 1L;
    private static final String USER_NAME = "user name";
    private static final String USER_LOGIN = "user login";

    private User user;
    private User userThreeParameters;
    private User userTwoParameters;
    private User userThreeParametersEquals;

    @Before                         //method will be run before any test in the class
    public void setUp() throws Exception{
        user = new User();
        userTwoParameters = new User(USER_LOGIN, USER_NAME);
        userThreeParameters = new User(USER_ID, USER_LOGIN, USER_NAME);
        userThreeParametersEquals = new User(USER_ID, USER_LOGIN, USER_NAME);
    }

    @Test
    public void testConstructorWithTwoParameters() throws Exception{
        assertEquals(USER_LOGIN, userTwoParameters.getLogin());
        assertEquals(USER_NAME, userTwoParameters.getUserName());
    }

    @Test
    public void testConstructorWithThreeParameters() throws Exception{
        assertEquals(USER_ID, userThreeParameters.getUserId());
        assertEquals(USER_LOGIN, userThreeParameters.getLogin());
        assertEquals(USER_NAME, userThreeParameters.getUserName());
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

    @Test
    public void testEquals() throws Exception{
        assertTrue(userThreeParameters.equals(userThreeParametersEquals));
        assertFalse(userTwoParameters.equals(userThreeParametersEquals));
    }
}
