package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-dao-test.xml"})
public class UserDaoImplTest {

    private static final Long REMOVE_TEST_ID = 2L;
    private static final Long ADD_TEST_ID = 5L;
    private static final Long SELECT_TEST_ID = 3L;
    private static final String SELECT_TEST_LOGIN = "elo4ka";
    private static final String USER_NAME = "tania";
    private static final String ADD_USER_LOGIN = "her login";

    @Autowired
    private UserDao userDao;

    @Test
    public void getUsers(){
        List<User> users = userDao.getUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    public void addCorrectUser(){
        List<User> users = userDao.getUsers();

        int sizeBefore = users.size();
        User user = new User();
        user.setUserId(ADD_TEST_ID);
        user.setUserName(USER_NAME);
        user.setLogin(ADD_USER_LOGIN);

        Long id = userDao.addUser(user);
        assertEquals(id, ADD_TEST_ID);

        users = userDao.getUsers();
        assertEquals(sizeBefore, users.size() - 1);
    }

    @Test
    public void removeCorrectUser(){
        List<User> users = userDao.getUsers();
        int sizeBefore = users.size();
        userDao.removeUser(REMOVE_TEST_ID);
        assertEquals(sizeBefore, userDao.getUsers().size() + 1);
    }

    @Test
    public void getUserById(){
        User user = userDao.getUserById(SELECT_TEST_ID);
        assertNotNull(user);
        assertTrue(user.getUserId() == SELECT_TEST_ID);
    }

    @Test
    public void getUserByLogin(){
        User user = userDao.getUserByLogin(SELECT_TEST_LOGIN);
        assertNotNull(user);
        assertEquals(SELECT_TEST_LOGIN, user.getLogin());
    }

    @Test
    public void getUsersByName(){
        List<User> users = userDao.getUsersByName(USER_NAME);
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    public void updateUser(){
        User updatedUser;
        User user = new User();
        user.setUserId(SELECT_TEST_ID);
        user.setUserName(USER_NAME + SELECT_TEST_LOGIN);
        user.setLogin(SELECT_TEST_LOGIN + USER_NAME);
        userDao.updateUser(user);
        updatedUser = userDao.getUserById(SELECT_TEST_ID);
        assertEquals(USER_NAME + SELECT_TEST_LOGIN, updatedUser.getUserName());
        assertEquals(SELECT_TEST_LOGIN + USER_NAME, updatedUser.getLogin());
    }
}