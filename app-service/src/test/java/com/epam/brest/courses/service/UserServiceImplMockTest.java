package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.UserDao;
import com.epam.brest.courses.domain.User;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertSame;
import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-service-mock-test.xml"})
public class UserServiceImplMockTest{
    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    private static final String SOME_LOGIN = "some login";

    @After
    public void clean() {
        reset(userDao);
    }

    private void addUser(User user, String login, boolean flag){
        userDao.addUser(user);
        expectLastCall().andReturn(3L);

        userDao.getUserByLogin(login);
        expectLastCall().andReturn(flag == true ? user : null);

        replay(userDao);
        Long id = userService.addUser(user);
        assertNotNull(id);
        assertEquals(id, (Long)3L);
        verify(userDao);
    }

    @Test
    public void addRightUser(){
        User user = UserDataFixture.getExistingUser(SOME_LOGIN);

        addUser(user, user.getLogin(), false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullUser() throws IllegalArgumentException{
        User user = UserDataFixture.getNullUser();

        addUser(user, null, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEmptyUser() throws IllegalArgumentException {
        User user = UserDataFixture.getEmptyUser();

        addUser(user, user.getLogin(), false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addUserWithNotNullId() throws IllegalArgumentException{
        User user = UserDataFixture.getUserWithNotNullUserId(15L);

        addUser(user, user.getLogin(), false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addUserWithNullName() throws IllegalArgumentException{
        User user = UserDataFixture.getNewUserWithNullName();

        addUser(user, user.getLogin(), false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addUserWithEmptyName() throws IllegalArgumentException{
        User user = UserDataFixture.getUserWithEmptyLogin();

        addUser(user, user.getLogin(), false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addUserWithNullLogin() throws IllegalArgumentException{
        User user = UserDataFixture.getUserWithNullLogin();

        addUser(user, user.getLogin(), false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addExistingUser() throws IllegalArgumentException{
        User user = UserDataFixture.getExistingUser(SOME_LOGIN);

        addUser(user, user.getLogin(), true);
    }

    @Test
    public void getUsers(){
        List<User> users = UserDataFixture.getExistingUsers();

        expect(userDao.getUsers()).andReturn(users);

        replay(userDao);
        List<User> result = userService.getUsers();
        verify(userDao);

        assertSame(users, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUserByEqualZeroId() throws IllegalArgumentException {
        User user = UserDataFixture.getExistingUserWithIdEqualsZero();

        expect(userDao.getUserById(user.getUserId())).andReturn(user);

        replay(userDao);
        userService.getUserById(user.getUserId());
        verify(userDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByLessThanZeroId() throws IllegalArgumentException {
        User user = UserDataFixture.getExistingUserWithIdLessZero();

        expect(userDao.getUserById(user.getUserId())).andReturn(user);

        replay(userDao);
        userService.getUserById(user.getUserId());
        verify(userDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByNullId() throws IllegalArgumentException {
        User user = UserDataFixture.getUserWithNullUserId();

        expect(userDao.getUserById(user.getUserId())).andReturn(user);

        replay(userDao);
        userService.getUserById(user.getUserId());
        verify(userDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByNullLogin() throws IllegalArgumentException {
        User user = UserDataFixture.getUserWithNullLogin();

        expect(userDao.getUserByLogin(user.getLogin())).andReturn(user);

        replay(userDao);
        userService.getUserByLogin(user.getLogin());
        verify(userDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByEmptyLogin() throws IllegalArgumentException {
        User user = UserDataFixture.getUserWithEmptyLogin();

        expect(userDao.getUserByLogin(user.getLogin())).andReturn(user);

        replay(userDao);
        userService.getUserById(user.getUserId());
        verify(userDao);
    }

    @Test
    public void testGetUsersByName() throws Exception {
        User user = UserDataFixture.getExistingUser(SOME_LOGIN);

        expect(userDao.getUserByLogin(user.getLogin())).andReturn(user);

        replay(userDao);
        User result = userService.getUserByLogin(user.getLogin());
        verify(userDao);

        assertSame(user, result);
    }

    @Test
    @Ignore("This test isn't ready yet!")
    public void testUpdateUser() throws Exception {

    }

    @Test
    @Ignore("This test isn't ready yet!")
    public void testRemoveUser() throws Exception {

    }


    //so on
}