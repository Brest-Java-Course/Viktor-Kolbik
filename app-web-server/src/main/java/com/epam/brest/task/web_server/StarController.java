package com.epam.brest.task.web_server;

import com.epam.brest.task.domain.Star;
import com.epam.brest.task.service.StarService;
import com.epam.brest.task.service.exception.BadParameterException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
@RequestMapping("/stars")
public class StarController {

    @Autowired
    private StarService starService;

    private static final Logger LOGGER = LogManager.getLogger(StarController.class);

    @RequestMapping("/")
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView("starPage", "stars", starService.getAllStars());

        return modelAndView;
    }

    @RequestMapping("/addStar")
    public ModelAndView addStar(@RequestParam("name") String name,
                                @RequestParam("age") Long age, @RequestParam("mass") Long mass,
                                @RequestParam("date") Date date, @RequestParam("galaxyId") Long galaxyId) {

        Star star = new Star(null, name, age, mass, date, galaxyId);
        ModelAndView modelAndView = null;

        try {
            Long i = starService.addStar(star);
            modelAndView = new ModelAndView("redirect:/stars/");
        } catch (BadParameterException e) {
            LOGGER.error(e.getMessage() + e.getObjectOfException());
            modelAndView = new ModelAndView("starPage", "wrongParameter", e.getObjectOfException());
            modelAndView.addObject("creationError", true);
            modelAndView.addObject("stars", starService.getAllStars());
        }

        return modelAndView;
    }

    @RequestMapping("/updateStar")
    public ModelAndView updateStar(@RequestParam("starId") Long starId, @RequestParam("name") String name,
                                   @RequestParam("age") Long age, @RequestParam("mass") Long mass,
                                   @RequestParam("date") Date date, @RequestParam("galaxyId") Long galaxyId) {

        Star star = new Star(starId, name, age, mass, date, galaxyId);
        ModelAndView modelAndView = null;

        try {
            starService.updateStar(star);
            modelAndView = new ModelAndView("redirect:/stars/");
        } catch (BadParameterException e) {
            LOGGER.error(e.getMessage() + e.getObjectOfException());
            modelAndView = new ModelAndView("starPage", "wrongParameter", e.getObjectOfException());
            modelAndView.addObject("updatingError", true);
            modelAndView.addObject("stars", starService.getAllStars());
        }

        return modelAndView;
    }
/*
    @RequestMapping("/setUpdateStarForm")
    public ModelAndView setUpdateForm(@RequestParam("starId") Long starId, @RequestParam("name") String name,
                                      @RequestParam("age") Long age, @RequestParam("mass") Long mass,
                                      @RequestParam("date") Date date, @RequestParam("galaxyId") Long galaxyId) {

        Star star = new Star(starId, name, age, mass, date, galaxyId);
        ModelAndView modelAndView = null;

        modelAndView = new ModelAndView("updateStarFormPage", "star", star);

        return modelAndView;
    }
*/
    @RequestMapping("/removeStar")
    public ModelAndView removeStar(@RequestParam("starId") Long starId) {

        ModelAndView modelAndView = null;

        try {
            starService.removeStar(starId);
            modelAndView = new ModelAndView("redirect:/stars/");
        } catch (BadParameterException e) {
            LOGGER.error(e.getMessage() + e.getObjectOfException());
            modelAndView = new ModelAndView("starPage", "wrongParameter", e.getObjectOfException());
            modelAndView.addObject("removingError", true);
            modelAndView.addObject("stars", starService.getAllStars());
        }

        return modelAndView;
    }
}