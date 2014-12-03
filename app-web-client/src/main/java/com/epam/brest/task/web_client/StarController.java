package com.epam.brest.task.web_client;

import com.epam.brest.task.domain.Star;
import com.epam.brest.task.rest_client.StarRestClient;
import com.epam.brest.task.service.exception.BadParameterException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.Set;

@Controller
@RequestMapping("/stars")
public class StarController {

    @Autowired
    private StarRestClient starRestClient;

    private static final Logger LOGGER = LogManager.getLogger(StarController.class);

    @RequestMapping("/")
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView("starPage", "stars", starRestClient.getAllStars());

        return modelAndView;
    }

    @RequestMapping("/addStar")
    public ModelAndView addStar(@RequestParam("name") String name,
                                @RequestParam("age") Long age, @RequestParam("mass") Long mass,
                                @RequestParam("date") Date date, @RequestParam("galaxyId") Long galaxyId) {

        Star star = new Star(null, name, age, mass, date, galaxyId);
        ModelAndView modelAndView = null;

        try {
            Long i = starRestClient.addStar(star);
            modelAndView = new ModelAndView("redirect:/stars/");
        } catch (BadParameterException e) {
            LOGGER.error(e.getMessage() + e.getObjectOfException());
            modelAndView = new ModelAndView("starPage", "wrongParameter", e.getObjectOfException());
            modelAndView.addObject("creationError", true);
            modelAndView.addObject("stars", starRestClient.getAllStars());
        }

        return modelAndView;
    }
/*
    @RequestMapping("/starsIntoGalaxy")
    public ModelAndView getStarsInGalaxy(@RequestParam("galaxyId") Long galaxyId){
        Set<Star> stars = null;
        ModelAndView modelAndView = null;

        try{
            System.out.println("!!!!!!!! + " + starRestClient);
            stars = starRestClient.getStarsByGalaxyId(galaxyId);
            modelAndView = new ModelAndView("starPage", "stars", stars);
        } catch(BadParameterException e){
            LOGGER.error(e.getMessage() + e.getObjectOfException());
            modelAndView = new ModelAndView("starPage", "wrongParameter", e.getObjectOfException());
            modelAndView.addObject("gettingError", true);
            modelAndView.addObject("stars", starRestClient.getAllStars());
        }

        return modelAndView;

    }
*/
    @RequestMapping("/updateStar")
    public ModelAndView updateStar(@RequestParam("starId") Long starId, @RequestParam("name") String name,
                                   @RequestParam("age") Long age, @RequestParam("mass") Long mass,
                                   @RequestParam("date") Date date, @RequestParam("galaxyId") Long galaxyId) {

        Star star = new Star(starId, name, age, mass, date, galaxyId);
        ModelAndView modelAndView = null;

        try {
            starRestClient.updateStar(star);
            modelAndView = new ModelAndView("redirect:/stars/");
        } catch (BadParameterException e) {
            LOGGER.error(e.getMessage() + e.getObjectOfException());
            modelAndView = new ModelAndView("starPage", "wrongParameter", e.getObjectOfException());
            modelAndView.addObject("updatingError", true);
            modelAndView.addObject("stars", starRestClient.getAllStars());
        }

        return modelAndView;
    }

    @RequestMapping("/removeStar")
    public ModelAndView removeStar(@RequestParam("starId") Long starId) {

        ModelAndView modelAndView = null;

        try {
            starRestClient.removeStar(starId);
            modelAndView = new ModelAndView("redirect:/stars/");
        } catch (BadParameterException e) {
            LOGGER.error(e.getMessage() + e.getObjectOfException());
            modelAndView = new ModelAndView("starPage", "wrongParameter", e.getObjectOfException());
            modelAndView.addObject("removingError", true);
            modelAndView.addObject("stars", starRestClient.getAllStars());
        }

        return modelAndView;
    }
}