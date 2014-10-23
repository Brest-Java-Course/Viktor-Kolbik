package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao{

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addUser(User user){
        jdbcTemplate.update("insert into USER (userId, login, name) values (?, ?, ?)", user.getUserId(), user.getLogin(), user.getUserName());
    }

    @Override
    public List<User> getUser(){
        return jdbcTemplate.query("select userId, login, name from USER", new UserMapper());
    }

    @Override
    public User getUserById(Long userId){
        return jdbcTemplate.queryForObject("select userId, login, name from USER where userId = ?", new Object[]{userId}, new UserMapper());
    }

    @Override
    public void removeUser(Long userId){
        jdbcTemplate.update("delete from USER where userId = ?", userId);
    }

    public class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();

            user.setUserId(rs.getLong("userId"));
            user.setUserName(rs.getString("name"));
            user.setLogin(rs.getString("login"));

            return user;
        }
    }
}
