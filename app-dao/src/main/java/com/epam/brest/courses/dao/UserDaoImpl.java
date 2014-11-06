package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
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
        * @author      Viktor Kolbik
        */
public class UserDaoImpl implements UserDao{
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

    public void setDataSource(DataSource dataSource){
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * adds new User into table
     * @param  user  user, we need to add.
     */
    @Override
    public Long addUser(final User user){
        LOGGER.debug("UserDaoImpl.addUser() starts for " + user);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource().addValue(USER_ID, user.getUserId())
                .addValue(LOGIN, user.getLogin()).addValue(USER_NAME, user.getUserName());
        namedParameterJdbcTemplate.update(addNewUserSql, parameters,  keyHolder);

        Long id = (Long)keyHolder.getKey();

        LOGGER.debug("UserDaoImpl.addUser() ends with " + id);

        return id;
    }
    /**
     * get all users from table
     * @return List stores all users in the table
     */
    @Override
    public List<User> getUsers(){
        return namedParameterJdbcTemplate.query(selectAllUsersSql, new UserMapper());
    }

    /**
     * get an user by his id
     * @param userId id of user who we need to get
     * @return a user with this id
     */
    @Override
    public User getUserById(Long userId){
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put(USER_ID, userId);
        return namedParameterJdbcTemplate.queryForObject(selectUserByIdSql, parameters, new UserMapper());
    }


    /**
     * get an user by his login
     * @param login login of user who we need to get
     * @return a user with this login
     */

    @Override
    public User getUserByLogin(String login){
        LOGGER.debug("UserDao.getUserByLogin() starts for " + login);
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put(LOGIN, login);
        User user = null;
//!!!!!!!!!!!!!!!org.springframework.dao.EmptyResultDataAccessException
        try{
            return namedParameterJdbcTemplate.queryForObject(selectUserByLoginSql, parameters, new UserMapper());
        }catch(EmptyResultDataAccessException e){
            LOGGER.debug("attemption to get user from UserDao.getUserByLogin() for " + login + "which doesn't exists");
            return user;
        }
    }


    /**
     * get an user by his id
     * @param userName userName of user who we need to get
     * @return a List users with this name
     */
    @Override
    public List<User> getUsersByName(String userName){
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put(USER_NAME, userName);
        return namedParameterJdbcTemplate.query(selectUsersByNameSql, parameters, new UserMapper());
    }


    /**
     * adds new User into bd
     * @param  user  user, we need to update.
     */
    @Override
    public void updateUser(User user){
        Map<String, Object> parameters = new HashMap<String, Object>(3);
        parameters.put(USER_ID, user.getUserId());
        parameters.put(LOGIN, user.getLogin());
        parameters.put(USER_NAME, user.getUserName());
        namedParameterJdbcTemplate.update(updateUserSql, parameters);
    }

    /**
     * adds new User into bd
     * @param  userId id of user, we need to remove.
     */
    @Override
    public void removeUser(Long userId){
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put(USER_ID, userId);
        namedParameterJdbcTemplate.update(removeUserSql, parameters);
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
