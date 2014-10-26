package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao{

    private static final String ADD_NEW_USER_SQL = "insert into SIMPLE_USER (user_id, login, user_name) values (:user_id, :login, :user_name)";
    private static final String REMOVE_USER_SQL = "delete from SIMPLE_USER where user_id = :user_id";
    private static final String UPDATE_USER_SQL = "update SIMPLE_USER set login = :login, user_name = :user_name where user_id = :user_id";

    private static final String SELECT_ALL_USERS_SQL = "select user_id, login, user_name from SIMPLE_USER";
    private static final String SELECT_USERS_BY_NAME_SQL = "select user_id, login, user_name from SIMPLE_USER where user_name = :user_name";
    private static final String SELECT_USER_BY_ID_SQL = "select user_id, login, user_name from SIMPLE_USER where user_id = :user_id";
    private static final String SELECT_USER_BY_LOGIN_SQL = "select user_id, login, user_name from SIMPLE_USER where login = :login";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String USER_ID = "user_id";
    private static final String LOGIN = "login";
    private static final String USER_NAME = "user_name";

    public void setDataSource(DataSource dataSource){
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void addUser(User user){
        Map<String, Object> parameters = new HashMap<String, Object>(3);
        parameters.put(USER_ID, user.getUserId());
        parameters.put(LOGIN, user.getLogin());
        parameters.put(USER_NAME, user.getUserName());
        namedParameterJdbcTemplate.update(ADD_NEW_USER_SQL, parameters);
    }

    @Override
    public List<User> getUsers(){
        //LOGGER.error("getUsers");
        return namedParameterJdbcTemplate.query(SELECT_ALL_USERS_SQL, new UserMapper());
    }

    @Override
    public User getUserById(Long userId){
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put(USER_ID, userId);
        return namedParameterJdbcTemplate.queryForObject(SELECT_USER_BY_ID_SQL, parameters, new UserMapper());
    }

    @Override
    public User getUserByLogin(String login){
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put(LOGIN, login);
        return namedParameterJdbcTemplate.queryForObject(SELECT_USER_BY_LOGIN_SQL, parameters, new UserMapper());
    }

    @Override
    public List<User> getUsersByName(String userName){
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put(USER_NAME, userName);
        return namedParameterJdbcTemplate.query(SELECT_USERS_BY_NAME_SQL, parameters, new UserMapper());
    }

    @Override
    public void updateUser(User user){
        Map<String, Object> parameters = new HashMap<String, Object>(3);
        parameters.put(USER_ID, user.getUserId());
        parameters.put(LOGIN, user.getLogin());
        parameters.put(USER_NAME, user.getUserName());
        namedParameterJdbcTemplate.update(UPDATE_USER_SQL, parameters);
    }

    @Override
    public void removeUser(Long userId){
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put(USER_ID, userId);
        namedParameterJdbcTemplate.update(REMOVE_USER_SQL, parameters);
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
