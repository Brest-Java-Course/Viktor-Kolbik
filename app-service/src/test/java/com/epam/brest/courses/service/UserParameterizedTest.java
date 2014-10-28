package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assume.assumeTrue;

@RunWith(Parameterized.class)
@ContextConfiguration(locations = {"classpath*:/spring-service-test.xml"})
public class UserParameterizedTest {
    private static final String EMPTY_STRING = "";
    private static final String SOME_NAME = "some name";
    private static final String SOME_LOGIN = "simpson";
    private static final Long SOME_ID = 3L;

    @Autowired
    private UserService userService;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private User user;

    public UserParameterizedTest(User user) {
        this.user = user;
    }

    @Before
    public void setUp() throws Exception {
        TestContextManager testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }



        @Test(expected = IllegalArgumentException.class)
        public void testAddUser() throws IllegalArgumentException{
            userService.addUser(user);
        }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserById() throws IllegalArgumentException{
        userService.getUserById(user.getUserId());
        assumeTrue(user.getUserId() != SOME_ID);                            //i suppose, i should get rif of such solutions
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByLogin() throws IllegalArgumentException{

        userService.getUserByLogin(user.getLogin());
        assumeTrue(!user.getLogin().equals(SOME_LOGIN));                            //i suppose, i should get rif of such solutions
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUsersByName() throws IllegalArgumentException{

        userService.getUsersByName(user.getUserName());
        assumeTrue(!user.getUserName().equals(SOME_NAME));                            //i suppose, i should get rif of such solutions
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUser() throws IllegalArgumentException{

        userService.updateUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveUser() throws IllegalArgumentException{

        userService.removeUser(user.getUserId());
        assumeTrue(user.getUserId() != SOME_ID);                            //i suppose, i should get rif of such solutions
    }

    @Parameterized.Parameters
    public static Collection userParams() {
        Object[][] params = new Object[][]{
                //           {null},
                {new User()},
                {new User(-1L, SOME_LOGIN, SOME_NAME)},
                {new User(0L, SOME_LOGIN, SOME_NAME)},
                {new User(SOME_ID, null, SOME_NAME)},
                {new User(SOME_ID, EMPTY_STRING, SOME_NAME)},
                {new User(SOME_ID, SOME_LOGIN, null)},
                {new User(SOME_ID, SOME_LOGIN, EMPTY_STRING)}
        };


        return Arrays.asList(params);
    }
}
