package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.UserDao;
import com.epam.brest.courses.domain.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.Assert.*;
import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-service-mock-test.xml"})
public class UserServiceImplMockTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    private static final String SOME_LOGIN = "some login";
    private static final String ADMIN_LOGIN = "admin";
    private static final Long SOME_ID = 3L;
    @After
    public void clean() {
        reset(userDao);
    }

    private void addUser(User user,  boolean flag) {
        userDao.addUser(user);
        expectLastCall().andReturn(SOME_ID);

        if(user != null)
        userDao.getUserByLogin(user.getLogin());

        if (flag) {
            expectLastCall().andThrow(new IllegalArgumentException("smth wrong with add user"));
        } else {
            expectLastCall().andReturn(user);
        }

        replay(userDao);
        Long id = userService.addUser(user);
        assertNotNull(id);
        assertEquals(id, SOME_ID);
        verify(userDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addCorrectUser() {
        User user = UserDataFixture.getCorrectUserById(SOME_ID);
        userDao.addUser(user);
        expectLastCall().andReturn(SOME_ID);

        userDao.getUserByLogin(user.getLogin());
        expectLastCall().andReturn(user);

        replay(userDao);
        Long id = userService.addUser(user);
        assertNotNull(id);
        assertEquals(id, SOME_ID);
        verify(userDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullUser() throws IllegalArgumentException {
        User user = UserDataFixture.getNullUser();

        addUser(user, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEmptyUser() throws IllegalArgumentException {
        User user = UserDataFixture.getEmptyUser();

        addUser(user, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addUserWithNotNullId() throws IllegalArgumentException {
        User user = UserDataFixture.getCorrectUserById(SOME_ID);

        addUser(user, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addUserWithNullName() throws IllegalArgumentException {
        User user = UserDataFixture.getUserWithNullName();

        addUser(user, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addUserWithEmptyName() throws IllegalArgumentException {
        User user = UserDataFixture.getUserWithEmptyLogin();

        addUser(user, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addUserWithNullLogin() throws IllegalArgumentException {
        User user = UserDataFixture.getUserWithNullLogin();

        addUser(user, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addExistingUser() throws IllegalArgumentException {
        User user = UserDataFixture.getCorrectUserById(SOME_ID);

        addUser(user, true);
    }

    @Test
    public void getUsers() {
        List<User> users = UserDataFixture.getCorrectUsers();

        expect(userDao.getUsers()).andReturn(users);

        replay(userDao);
        List<User> result = userService.getUsers();
        verify(userDao);

        assertSame(users, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByLessThanZeroId() throws IllegalArgumentException {
        User user = UserDataFixture.getCorrectUserById(-1L);

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
        userService.getUserByLogin(user.getLogin());
        verify(userDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUsersByNullName() throws IllegalArgumentException {
        User user = UserDataFixture.getUserWithNullName();
        List<User> users = UserDataFixture.getCorrectUsers();
        expect(userDao.getUsersByName(user.getUserName())).andReturn(users);

        replay(userDao);
        userService.getUsersByName(user.getUserName());
        verify(userDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByEmptyName() throws IllegalArgumentException {
        User user = UserDataFixture.getUserWithEmptyName();
        List<User> users = UserDataFixture.getCorrectUsers();
        expect(userDao.getUsersByName(user.getUserName())).andReturn(users);

        replay(userDao);
        userService.getUsersByName(user.getUserName());
        verify(userDao);
    }

    private void updateUser(User user) {
        userDao.updateUser(user);
        expectLastCall();

        if(user != null) {
            userDao.getUserByLogin(user.getLogin());
            expectLastCall().andReturn(user);
        }


        replay(userDao);
        userService.updateUser(user);
        verify(userDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNullUser() throws IllegalArgumentException {
        User user = UserDataFixture.getNullUser();

        updateUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateEmptyUser() throws IllegalArgumentException {
        User user = UserDataFixture.getEmptyUser();

        updateUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUserWithNullId() throws IllegalArgumentException {
        User user = UserDataFixture.getUserWithNullUserId();

        updateUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUserWithAdminId() throws IllegalArgumentException {
        User user = UserDataFixture.getCorrectUserById(0L);

        updateUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUserWithAdminLogin() throws IllegalArgumentException {
        User user = UserDataFixture.getCorrectUserByLogin(ADMIN_LOGIN);

        updateUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUserWithNullName() throws IllegalArgumentException {
        User user = UserDataFixture.getUserWithNullName();

        updateUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUserWithEmptyName() throws IllegalArgumentException {
        User user = UserDataFixture.getUserWithEmptyLogin();

        updateUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateUserWithNullLogin() throws IllegalArgumentException {
        User user = UserDataFixture.getUserWithNullLogin();

        updateUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeUserWithNullId() throws IllegalArgumentException {
        User user = UserDataFixture.getUserWithNullUserId();
        userDao.removeUser(user.getUserId());
        expectLastCall();

        replay(userDao);
        userService.removeUser(user.getUserId());

        verify(userDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeUserWithLessZeroId() throws IllegalArgumentException {
        User user = UserDataFixture.getCorrectUserById(-1L);
        userDao.removeUser(user.getUserId());
        expectLastCall();

        replay(userDao);
        userService.removeUser(user.getUserId());

        verify(userDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeAdmin() throws IllegalArgumentException {
        User user = UserDataFixture.getCorrectUserById(0L);
        userDao.removeUser(user.getUserId());
        expectLastCall();

        replay(userDao);
        userService.removeUser(user.getUserId());

        verify(userDao);
    }
}