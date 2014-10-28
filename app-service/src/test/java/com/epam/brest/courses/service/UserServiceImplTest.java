package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-service-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    private static final String ADMIN_LOGIN = "admin";
    private static final String SOME_LOGIN = "some login";
    private static final String SOME_NAME = "some name";
    private static final String EMPTY_STRING = "";
    private static final String EXISTING_LOGIN = "simpson";
    private static final Long EXISTING_ID = 4L;
    private static final Long ADMIN_ID = 1L;

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullUser() throws IllegalArgumentException {
        userService.addUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEmptyUser() throws IllegalArgumentException {
        userService.addUser(new User());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNotNullUserId()throws IllegalArgumentException{
        userService.addUser(new User(10L, SOME_LOGIN, SOME_NAME));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithNullName() throws IllegalArgumentException {
        userService.addUser(new User(null, SOME_LOGIN, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithEmptyName() throws IllegalArgumentException{
        userService.addUser(new User(null, SOME_LOGIN, EMPTY_STRING));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithNullLogin() throws IllegalArgumentException {
        userService.addUser(new User(null, null, SOME_NAME));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithEmptyLogin() throws IllegalArgumentException{
        userService.addUser(new User(null, EMPTY_STRING, SOME_NAME));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithExistingLogin() throws IllegalArgumentException{
        userService.addUser(new User(null, EXISTING_LOGIN, SOME_NAME));
    }

    @Test
    public void testGetUsers() throws Exception {
        List<User> users = userService.getUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByEqualZeroId() throws IllegalArgumentException {
        userService.getUserById(0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByLessThanZeroId() throws IllegalArgumentException {
        userService.getUserById(-1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByNullId() throws IllegalArgumentException {
        userService.getUserById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByNullLogin() throws IllegalArgumentException {
        userService.getUserByLogin(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByEmptyLogin() throws IllegalArgumentException {
        userService.getUserByLogin(EMPTY_STRING);
    }

    @Test
    public void testGetUsersByName() throws Exception {
        userService.getUsersByName(EXISTING_LOGIN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullUser() throws IllegalArgumentException {
        userService.updateUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateEmptyUser() throws IllegalArgumentException {
        userService.updateUser(new User());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullUserId() throws IllegalArgumentException{
        userService.updateUser(new User(null, SOME_LOGIN, SOME_NAME));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserWithNullName() throws IllegalArgumentException {
        userService.updateUser(new User(null, SOME_LOGIN, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserWithEmptyName() throws IllegalArgumentException{
        userService.updateUser(new User(EXISTING_ID, SOME_LOGIN, EMPTY_STRING));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserWithNullLogin() throws IllegalArgumentException {
        userService.updateUser(new User(EXISTING_ID, null, SOME_NAME));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserWithEmptyLogin() throws IllegalArgumentException{
        userService.updateUser(new User(EXISTING_ID, EMPTY_STRING, SOME_NAME));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserWithExistingLogin() throws IllegalArgumentException{
        userService.updateUser(new User(EXISTING_ID, EXISTING_LOGIN, SOME_NAME));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserWithAdminIdAndLogin() throws IllegalArgumentException{
        userService.updateUser(new User(ADMIN_ID, ADMIN_LOGIN, SOME_NAME));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveUserWithEqualsZeroId() throws IllegalArgumentException {
        userService.removeUser(0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveUserWithLessThanZeroId() throws IllegalArgumentException {
        userService.removeUser(-1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveUserWithNullId() throws IllegalArgumentException {
        userService.removeUser(null);
    }

}