package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;
import org.springframework.beans.factory.annotation.Value;
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

public class UserDaoImpl implements UserDao{
    @Value("#{T(org.apache.commons.io.FileUtils).readFileToString((new org.springframework.core.io.ClassPathResource('${insert-into-user-path}')).file)}")
    public String addNewUserSql;
    @Value("#{T(org.apache.commons.io.FileUtils).readFileToString((new org.springframework.core.io.ClassPathResource('${delete-from-user-path}')).file)}")
    public String removeUserSql;
    @Value("#{T(org.apache.commons.io.FileUtils).readFileToString((new org.springframework.core.io.ClassPathResource('${update-user-path}')).file)}")
    public String updateUserSql;

    @Value("#{T(org.apache.commons.io.FileUtils).readFileToString((new org.springframework.core.io.ClassPathResource('${select-all-users-path}')).file)}")
    public String selectAllUsersSql;
    @Value("#{T(org.apache.commons.io.FileUtils).readFileToString((new org.springframework.core.io.ClassPathResource('${select-users-by-name-path}')).file)}")
    public String selectUsersByNameSql;
    @Value("#{T(org.apache.commons.io.FileUtils).readFileToString((new org.springframework.core.io.ClassPathResource('${select-user-by-id-path}')).file)}")
    public String selectUserByIdSql;
    @Value("#{T(org.apache.commons.io.FileUtils).readFileToString((new org.springframework.core.io.ClassPathResource('${select-user-by-login-path}')).file)}")
    public String selectUserByLoginSql;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    //private static final Logger LOGGER = LogManager.getLogger();

    private static final String USER_ID = "user_id";
    private static final String LOGIN = "login";
    private static final String USER_NAME = "user_name";

    public void setDataSource(DataSource dataSource){
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Long addUser(final User user){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource().addValue(USER_ID, user.getUserId())
                .addValue(LOGIN, user.getLogin()).addValue(USER_NAME, user.getUserName());
        namedParameterJdbcTemplate.update(addNewUserSql, parameters,  keyHolder);

        return (Long)keyHolder.getKey();
    }

    @Override
    public List<User> getUsers(){
        return namedParameterJdbcTemplate.query(selectAllUsersSql, new UserMapper());
    }

    @Override
    public User getUserById(Long userId){
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put(USER_ID, userId);
        return namedParameterJdbcTemplate.queryForObject(selectUserByIdSql, parameters, new UserMapper());
    }

    @Override
    public User getUserByLogin(String login){
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put(LOGIN, login);
        return namedParameterJdbcTemplate.queryForObject(selectUserByLoginSql, parameters, new UserMapper());
    }

    @Override
    public List<User> getUsersByName(String userName){
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put(USER_NAME, userName);
        return namedParameterJdbcTemplate.query(selectUsersByNameSql, parameters, new UserMapper());
    }

    @Override
    public void updateUser(User user){
        Map<String, Object> parameters = new HashMap<String, Object>(3);
        parameters.put(USER_ID, user.getUserId());
        parameters.put(LOGIN, user.getLogin());
        parameters.put(USER_NAME, user.getUserName());
        namedParameterJdbcTemplate.update(updateUserSql, parameters);
    }

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
