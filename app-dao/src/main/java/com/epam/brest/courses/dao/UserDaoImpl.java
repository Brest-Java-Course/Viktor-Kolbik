package com.epam.brest.courses.dao;

import com.epam.brest.courses.dao.exception.*;
import com.epam.brest.courses.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * presents DAO class, that provides access to DB
 * @author Viktor Kolbik
 */
public class UserDaoImpl implements UserDao {
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${insert-into-user-path}')).inputStream)}")
    public String addNewUserSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${delete-from-user-path}')).inputStream)}")
    public String removeUserSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${update-user-path}')).inputStream)}")
    public String updateUserSql;

    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-all-users-path}')).inputStream)}")
    public String selectAllUsersSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-users-by-name-path}')).inputStream)}")
    public String selectUsersByNameSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-user-by-id-path}')).inputStream)}")
    public String selectUserByIdSql;
    @Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select-user-by-login-path}')).inputStream)}")
    public String selectUserByLoginSql;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String USER_ID = "user_id";
    private static final String LOGIN = "login";
    private static final String USER_NAME = "user_name";

    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * adds new User into table
     * @param user user, we need to add.
     * @return a new user's id
     */
    @Override
    public Long addUser(final User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource().addValue(USER_ID, user.getUserId())
                .addValue(LOGIN, user.getLogin()).addValue(USER_NAME, user.getUserName());
        try {
            namedParameterJdbcTemplate.update(addNewUserSql, parameters, keyHolder);
        }catch(DataAccessException e){
            LOGGER.error("Error while creating user ...", e);
            throw new UserCreationException("Can't create user", user);
        }

        Long id = (Long) keyHolder.getKey();

        return id;
    }

    /**
     * get all users from table
     * @return List stores all users in the table
     */
    @Override
    public List<User> getUsers() {
        List<User> list = null;

        try{
            list = namedParameterJdbcTemplate.query(selectAllUsersSql, new UserMapper());
        }catch(EmptyResultDataAccessException e) {
            LOGGER.error("Attemption to get users from empty BD.");
            throw new UsersNotFoundException("There are no usrers in db.");
        }

        return list;
    }

    /**
     * get an user by his id
     * @param userId id of user who we need to get
     * @throws com.epam.brest.courses.dao.exception.UserForIdNotFoundException
     * @return a user with this id
     */
    @Override
    public User getUserById(Long userId) {
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        User user = null;
        parameters.put(USER_ID, userId);
        try{
            user = namedParameterJdbcTemplate.queryForObject(selectUserByIdSql, parameters, new UserMapper());
        }catch(EmptyResultDataAccessException e){
            LOGGER.error("Attemption to get an unexisting user from DB by ID = " + userId + ". ");
            throw new UserForIdNotFoundException("There is no a user with that ID in bd.", userId);
        }

        return user;
    }

    /**
     * get an user by his login
     *
     * @param login login of user who we need to get
     * @return a user with this login
     */

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put(LOGIN, login);

        try {
            user =  namedParameterJdbcTemplate.queryForObject(selectUserByLoginSql, parameters, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Attemption to get an unexisting user from DB by login = " + login + ". ");
            throw new UserForLoginNotFoundException("There is no a user with that login in bd.", login);
        }

        return user;
    }


    /**
     * get an user by his id
     *
     * @param userName userName of user who we need to get
     * @return a List users with this name
     */
    @Override
    public List<User> getUsersByName(String userName) {
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        List<User> list = null;

        parameters.put(USER_NAME, userName);
        try{
            list = namedParameterJdbcTemplate.query(selectUsersByNameSql, parameters, new UserMapper());
        }catch(EmptyResultDataAccessException e){
            LOGGER.error("Attempting to get an unexisting users from DB by name = " + userName + ". ");
            throw new UsersForNameNotFoundException("There are not users with that name in BD", userName);
        }

        return list;
    }


    /**
     * adds new User into bd
     * @param user user, we need to update.
     */
    @Override
    public void updateUser(User user) {
        Map<String, Object> parameters = new HashMap<String, Object>(3);
        parameters.put(USER_ID, user.getUserId());
        parameters.put(LOGIN, user.getLogin());
        parameters.put(USER_NAME, user.getUserName());

        try {
          namedParameterJdbcTemplate.update(updateUserSql, parameters);
        }catch(DataAccessException e){
            LOGGER.error("Exception while updating user " + user);
            throw new UserUpdatingException("Error while updating user ", user);
        }
    }

    /**
     * adds new User into bd
     * @param userId id of user, we need to remove.
     */
    @Override
    public void removeUser(Long userId) {
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put(USER_ID, userId);
        try {
            namedParameterJdbcTemplate.update(removeUserSql, parameters);
        }catch(DataAccessException e){
            LOGGER.error("Exception while removing user for id = " + userId);
            throw new UserRemovingEcxeption("Exception while removing user.", userId);
        }
    }
    public class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();

            user.setUserId(rs.getLong("user_id"));
            user.setUserName(rs.getString("user_name"));
            user.setLogin(rs.getString("login"));

            return user;
        }
    }
}
