package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.UserDao;
import com.epam.brest.courses.domain.User;
import com.epam.brest.courses.service.exception.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

//@Service
public class UserServiceImpl implements UserService {

    private static final String USER_UPDATING_EXCEPTION_MSG = "Exception while updating user";
    private static final String GET_USER_BY_LOGIN_EXCEPTION_MSG = "Exception while getting user by login";
    private static final String LOGIN_IS_OCCUPIED_ERROR = "User with such login has already existed!";
    private static final String EMPTY_STRING = "";
    private static final Long LOW_BORDER_OF_ID = -1L;
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    private static final String GET_USER_BY_ID_EXCEPTION_MSG = "Exception while getting user by ID";
    private static final String USER_CREATION_EXCEPTION_MSG = "Exception while creation user";
    private static final String GET_USERS_EXCEPTION_MSG = "Exception while getting all users";
    private static final String GET_USERS_BY_NAME_EXCEPTION_MSG = "Exception while getting users by name";
    private static final String LOGIN_DOES_NOT_EXIST_ERROR = "User for that login doesn't exist";
    private static final String USER_REMOVING_EXCEPTION_MSG = "Exception while updating user";
    private static final String USER_WITH_LOGIN_EXIST = "User with such login has already existed";
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public Long addUser(User user) {

        Assert.notNull(user);
        Assert.isNull(user.getUserId());
        Assert.isTrue(user.getUserName() != null && !user.getUserName().equals(EMPTY_STRING));
        Assert.isTrue(user.getLogin() != null && !user.getLogin().equals(EMPTY_STRING));

        try {
            userDao.getUserByLogin(user.getLogin());

            LOGGER.debug(LOGIN_IS_OCCUPIED_ERROR);
            throw new IllegalArgumentException(LOGIN_IS_OCCUPIED_ERROR);

        } catch (EmptyResultDataAccessException e) {
            return userDao.addUser(user);
        } catch (DataAccessException e) {
            LOGGER.error(USER_CREATION_EXCEPTION_MSG);
            throw new UserCreationException(USER_CREATION_EXCEPTION_MSG, user);
        }
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        try {
            return userDao.getUsers();
        } catch (DataAccessException e) {
            LOGGER.error(GET_USERS_EXCEPTION_MSG);
            throw new UsersNotFoundException(GET_USERS_EXCEPTION_MSG);
        }
    }

    @Override
    @Transactional
    public User getUserById(Long userId) {
        Assert.isTrue(userId != null && userId > LOW_BORDER_OF_ID);

        try {
            return userDao.getUserById(userId);
        } catch (DataAccessException e) {
            LOGGER.error(GET_USER_BY_ID_EXCEPTION_MSG, userId);
            throw new UserForIdNotFoundException(GET_USER_BY_ID_EXCEPTION_MSG, userId);
        }
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {
        Assert.isTrue(login != null && !login.equals(EMPTY_STRING));

        try {
            return userDao.getUserByLogin(login);
        } catch (DataAccessException e) {
            LOGGER.error(GET_USER_BY_LOGIN_EXCEPTION_MSG, login);
            throw new UserForLoginNotFoundException(GET_USER_BY_LOGIN_EXCEPTION_MSG, login);
        }
    }

    @Override
    @Transactional
    public List<User> getUsersByName(String userName) {

        Assert.isTrue(userName != null && !userName.equals(EMPTY_STRING));
        try {
            return userDao.getUsersByName(userName);
        }catch(DataAccessException e){
            LOGGER.error(GET_USERS_BY_NAME_EXCEPTION_MSG);
            throw new UsersForNameNotFoundException(GET_USERS_BY_NAME_EXCEPTION_MSG, userName);
        }
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        Assert.notNull(user);
        Assert.isTrue(user.getUserId() != null);
        Assert.isTrue(user.getUserName() != null && !user.getUserName().equals(EMPTY_STRING));
        Assert.isTrue(user.getLogin() != null && !user.getLogin().equals(EMPTY_STRING));

        Assert.isTrue((user.getUserId() == 0L && "admin".equals(user.getLogin().toLowerCase()))
                || (!"admin".equals(user.getLogin().toLowerCase()) && user.getUserId() != 0L));

        Assert.isTrue(!"admin".equals(user.getLogin().toLowerCase()) || user.getUserId() == 0L);

        User userFromDB = null;

        try {
            userDao.getUserById(user.getUserId());
            userFromDB = userDao.getUserByLogin(user.getLogin());

            if(userFromDB.getUserId() == user.getUserId())
                userDao.updateUser(user);
            else{
                LOGGER.debug(USER_WITH_LOGIN_EXIST);
                throw new IllegalArgumentException(USER_WITH_LOGIN_EXIST);
            }
        } catch (EmptyResultDataAccessException e) {
            userDao.updateUser(user);
        }catch(DataAccessException e){
            LOGGER.debug(USER_UPDATING_EXCEPTION_MSG);
            throw new UserUpdatingException(USER_UPDATING_EXCEPTION_MSG, user);
        }

    }

    @Override
    @Transactional
    public void removeUser(Long userId) {
        Assert.isTrue(userId != null && userId > LOW_BORDER_OF_ID);
        Assert.isTrue(userId != 0L);
        try {
            userDao.removeUser(userId);
        }catch(DataAccessException e){
            LOGGER.debug(USER_REMOVING_EXCEPTION_MSG);
            throw new UserRemovingEcxeption(USER_REMOVING_EXCEPTION_MSG, userId);
        }
    }
}
