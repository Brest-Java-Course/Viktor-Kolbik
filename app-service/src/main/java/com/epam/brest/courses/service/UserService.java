package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.User;
import java.util.List;

public interface UserService {
    public Long addUser(User user);
    public List<User> getUsers();
    public User getUserById(Long userId);
    public User getUserByLogin(String login);
    public List<User> getUsersByName(String userName);
    public void updateUser(User user);
    public void removeUser(Long userId);
}
