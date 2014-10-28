package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
@ContextConfiguration(locations = {"classpath*:/spring-service-test.xml"})
public class UserParameterizedTest {
    private static final String SOME_NAME = "some name";
    private static final String SOME_LOGIN = "some login";
    private static final Long   SOME_ID = 3L;

    @Autowired
    private UserService userService;

    //private User user;
    private Long userId;

   /* public UserParameterizedTest(User user){
        this.user = user;
    }
*/
    public UserParameterizedTest(/*User user,*/ Long userId){
/*        this.user = user;*/
        this.userId = userId;
    }

    @Before
    public void setUp() throws Exception{
        TestContextManager testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }
/*
    @Test(expected = IllegalArgumentException.class)
    public void test() throws IllegalArgumentException{
        userService.addUser(user);
    }
*/
    @Test(expected = IllegalArgumentException.class)
    public void testGetUserById() throws IllegalArgumentException{
        userService.getUserById(userId);
    }
/*
    @Parameterized.Parameters
    public static Collection userParams(){
        Object[][] params = new Object[][]{
                {null},
                {new User()},
                {new User(SOME_ID, SOME_LOGIN, SOME_NAME)}
        };

        return Arrays.asList(params);
    }
*/
    @Parameterized.Parameters
    public static Collection longParams(){
        Object[][] params = new Object[][]{
                {null},
                {-1L},
                {0L},
        };

        return Arrays.asList(params);
    }
}
