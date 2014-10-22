package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/testApplicationContextSpring.xml"})
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void getUsers(){
        List<User> users = userDao.getUser();
      //  JOptionPane(null, users.get(0).get);
        assertNotNull(users);
        assertFalse(users.isEmpty());

    }

    @Test
    public void addUser(){
        List<User> users = userDao.getUser();

        int sizeBefore = users.size();
        User user = new User();
        user.setUserId(3L);
        user.setUserName("tanja");
        user.setLogin("tt");

        userDao.addUser(user);

        users = userDao.getUser();
        assertEquals(sizeBefore, users.size() - 1);

    }
}