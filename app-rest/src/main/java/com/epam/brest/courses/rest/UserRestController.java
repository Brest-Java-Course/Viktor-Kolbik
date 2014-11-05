package com.epam.brest.courses.rest;

import com.epam.brest.courses.domain.User;
import com.epam.brest.courses.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserRestController {
    @Resource
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = null;
        try {
            user = userService.getUserById(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity("User not found for id = " + id + "error: " + ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/login/{login}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByLogin(@PathVariable String login) {
            User user = userService.getUserByLogin(login);
            return new ResponseEntity(user, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsersByName(@PathVariable String name) {
        List users = userService.getUsersByName(name);
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers() {
        List users = userService.getUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> addUser(@RequestBody User user) {
        Long id = userService.addUser(user);
        return new ResponseEntity(id, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity("User for id = " + user.getUserId() + " was updated.", HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeUser(@PathVariable Long id) {
        userService.removeUser(id);
        return new ResponseEntity("User for id = " + id + " was removed", HttpStatus.OK);
    }
}
