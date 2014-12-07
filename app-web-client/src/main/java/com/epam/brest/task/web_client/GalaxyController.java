package com.epam.brest.task.web_client;

import com.epam.brest.task.domain.Galaxy;
import com.epam.brest.task.rest_client.GalaxyRestClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
@RequestMapping("/galaxies")
public class GalaxyController {

    @Autowired
    private GalaxyRestClient galaxyRestClient;

    private static final Logger LOGGER = LogManager.getLogger(GalaxyController.class);

    @RequestMapping("/")
    public ModelAndView galaxyPage() {

        ModelAndView modelAndView = new ModelAndView("galaxyPage", "galaxies", galaxyRestClient.getAllGalaxies());

        return modelAndView;
    }

    @RequestMapping("/addGalaxy")
    public ModelAndView addGalaxy(@RequestParam("name") String name,
                                  @RequestParam("distance") Long distance,
                                  @RequestParam("date") Date date) {
        Galaxy galaxy = new Galaxy(null, name, distance, date);
        ModelAndView modelAndView = null;

        try {

            Long i = galaxyRestClient.addGalaxy(galaxy);
            modelAndView = new ModelAndView("redirect:/galaxies/");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            modelAndView = new ModelAndView("galaxyPage", "galaxies", galaxyRestClient.getAllGalaxies());
            modelAndView.addObject("creationError", true);
        }

        return modelAndView;
    }

    @RequestMapping("/updateGalaxy")
    public ModelAndView updateGalaxy(@RequestParam("galaxyId") Long galaxyId,
                                     @RequestParam("name") String name,
                                     @RequestParam("distance") Long distance,
                                     @RequestParam("date") Date date) {

        Galaxy galaxy = new Galaxy(galaxyId, name, distance, date);
        ModelAndView modelAndView = null;

        try{
            galaxyRestClient.updateGalaxy(galaxy);
            modelAndView = new ModelAndView("redirect:/galaxies/");
        } catch(Exception e){
            LOGGER.error(e.getMessage());
            modelAndView = new ModelAndView("galaxyPage", "updatingError", true);
            modelAndView.addObject("galaxies", galaxyRestClient.getAllGalaxies());
        }

        return modelAndView;
    }

    @RequestMapping("/removeGalaxy")
    public ModelAndView removeGalaxy(@RequestParam("galaxyId") Long galaxyId){
        ModelAndView modelAndView = null;

        try{
            galaxyRestClient.removeGalaxy(galaxyId);
            modelAndView = new ModelAndView("redirect:/galaxies/");
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            modelAndView = new ModelAndView("galaxyPage", "removingError", true);
            modelAndView.addObject("galaxies", galaxyRestClient.getAllGalaxies());
        }

        return modelAndView;
    }
}
