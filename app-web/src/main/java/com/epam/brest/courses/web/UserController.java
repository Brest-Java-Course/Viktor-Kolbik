package com.epam.brest.courses.web;


import com.epam.brest.courses.domain.User;
import com.epam.brest.courses.service.UserService;
import com.epam.brest.courses.service.exception.UserUpdatingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mvc")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @RequestMapping(value = {"/"})
    public ModelAndView launchInputForm(){
        ModelAndView modelAndView = new ModelAndView("inputForm", "users", userService.getUsers());

        return modelAndView;
    }

    @RequestMapping(value = {"/submitData"})
    public ModelAndView getInputForm(@RequestParam("name") String name, @RequestParam("login") String login){
        User user = new User(login, name);
        ModelAndView modelAndView = null;

        try {
            userService.addUser(user);
            modelAndView = new ModelAndView("redirect:/mvc/");
        }catch(IllegalArgumentException e){
            modelAndView = new ModelAndView("inputForm", "errorCreation", true);
            modelAndView.addObject("users", userService.getUsers());
        }

        return modelAndView;
    }

    @RequestMapping(value = {"/fillInUpdateForm"})
    public ModelAndView setUpdateForm(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("login") String login){
        User user = new User(id, login, name);
        ModelAndView modelAndView = new ModelAndView("updateForm", "user", user);

        return modelAndView;
    }

    @RequestMapping(value = {"/updateUser"})
    public ModelAndView updateUser(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("login") String login){
        User user = new User(id, login, name);
        ModelAndView modelAndView = null;

        try {
            userService.updateUser(user);
            modelAndView = new ModelAndView("redirect:/mvc/");
            return modelAndView;
        }catch(IllegalArgumentException e){
            modelAndView = new ModelAndView("updateForm", "userExistedError", true);
            modelAndView.addObject("user", user);
            return modelAndView;
        }catch(UserUpdatingException e){
            modelAndView = new ModelAndView("updateForm", "userExistedError", true);
            modelAndView.addObject("user", user);
            return modelAndView;
        }

    }

    @RequestMapping(value = {"/removeUser"})
    public ModelAndView removeUser(@RequestParam("id") Long id){
        ModelAndView modelAndView = null;
        try {
            userService.removeUser(id);
            return new ModelAndView("redirect:/mvc/");
        }catch(IllegalArgumentException e) {
            modelAndView = new ModelAndView("inputForm", "errorRemoving", true);
            modelAndView.addObject("users", userService.getUsers());
            return modelAndView;
        }
    }

    @RequestMapping(value = {"/usersByName"})
    public ModelAndView usersByName(@RequestParam("name") String name){
            ModelAndView modelAndView = null;
        try {
            modelAndView = new ModelAndView("inputForm", "users", userService.getUsersByName(name));
            modelAndView.addObject("usersByName", true);
            return modelAndView;
        }catch(IllegalArgumentException e) {
            return new ModelAndView("inputForm", "errorRemoving", true);
        }
    }
}