package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;
import java.util.List;

public interface  UserDao {

    public void addUser(User user);
    public List<User> getUser();
    public User getUserById(Long id);
    public void removeUser(Long userId);
}
