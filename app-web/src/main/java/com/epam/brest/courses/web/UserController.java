package com.epam.brest.courses.web;


import com.epam.brest.courses.domain.User;
import com.epam.brest.courses.service.UserService;
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

    @RequestMapping(value = {"/"})
    public ModelAndView launchInputForm(){
        ModelAndView modelAndView = new ModelAndView("inputForm", "user", new User());

        return modelAndView;
    }

    @RequestMapping(value = {"/submitData"})
    public ModelAndView getInputForm(@RequestParam("name") String name, @RequestParam("login") String login){
        User user = new User(login, name);
        Long id = userService.addUser(user);
        user.setUserId(id);
        ModelAndView displayResult = new ModelAndView("displayResult", "user", user);

        return displayResult;
    }
}
