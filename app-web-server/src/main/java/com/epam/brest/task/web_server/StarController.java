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
import java.util.Set;

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
    public ModelAndView addStar(@RequestParam("name") final String name,
                                @RequestParam("age") final Long age, @RequestParam("mass") final Long mass,
                                @RequestParam("date") final Date date, @RequestParam("galaxyId") final Long galaxyId) {

        Star star = new Star(null, name, age, mass, date, galaxyId);
        ModelAndView modelAndView = null;

        try {
            Long i = starService.addStar(star);
            modelAndView = new ModelAndView("redirect:/stars/");
        } catch (BadParameterException e) {
            LOGGER.error(e.getMessage() + e.getObjectOfException());
            modelAndView = new ModelAndView("starPage", "wrongParameter", e.getMessage() + e.getObjectOfException());
            modelAndView.addObject("creationError", true);
            modelAndView.addObject("stars", starService.getAllStars());
        }

        return modelAndView;
    }

    @RequestMapping("/starsIntoGalaxy")
    public ModelAndView getStarsInGalaxy(@RequestParam("galaxyId") final Long galaxyId){
        Set<Star> stars = null;
        ModelAndView modelAndView = null;

        try{
            stars = starService.getStarsByGalaxyId(galaxyId);
            modelAndView = new ModelAndView("starPage", "stars", stars);
        } catch(BadParameterException e){
            LOGGER.error(e.getMessage() + e.getObjectOfException());
            modelAndView = new ModelAndView("starPage", "wrongParameter", e.getMessage() + e.getObjectOfException());
            modelAndView.addObject("gettingError", true);
            modelAndView.addObject("stars", starService.getAllStars());
        }

        return modelAndView;

    }

    @RequestMapping("/updateStar")
    public ModelAndView updateStar(@RequestParam("starId") final Long starId, @RequestParam("name") final String name,
                                   @RequestParam("age") final Long age, @RequestParam("mass") final Long mass,
                                   @RequestParam("date") final Date date, @RequestParam("galaxyId") final Long galaxyId) {

        Star star = new Star(starId, name, age, mass, date, galaxyId);
        ModelAndView modelAndView = null;

        try {
            starService.updateStar(star);
            modelAndView = new ModelAndView("redirect:/stars/");
        } catch (BadParameterException e) {
            LOGGER.error(e.getMessage() + e.getObjectOfException());
            modelAndView = new ModelAndView("starPage", "wrongParameter", e.getMessage() + e.getObjectOfException());
            modelAndView.addObject("updatingError", true);
            modelAndView.addObject("stars", starService.getAllStars());
        }

        return modelAndView;
    }

    @RequestMapping("/removeStar")
    public ModelAndView removeStar(@RequestParam("starId") final Long starId) {

        ModelAndView modelAndView = null;

        try {
            starService.removeStar(starId);
            modelAndView = new ModelAndView("redirect:/stars/");
        } catch (BadParameterException e) {
            LOGGER.error(e.getMessage() + e.getObjectOfException());
            modelAndView = new ModelAndView("starPage", "wrongParameter", e.getMessage() + e.getObjectOfException());
            modelAndView.addObject("removingError", true);
            modelAndView.addObject("stars", starService.getAllStars());
        }

        return modelAndView;
    }
}