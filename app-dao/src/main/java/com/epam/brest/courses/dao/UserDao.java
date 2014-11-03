package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;
import java.util.List;

public interface  UserDao {
    public Long addUser(User user);
    public List<User> getUsers();
    public User getUserById(Long id);
    public User getUserByLogin(String login);
    public List<User> getUsersByName(String userName);
    public void updateUser(User user);
    public void removeUser(Long userId);
}
