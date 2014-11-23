package com.epam.brest.task.rest_server;


import com.epam.brest.task.domain.Galaxy;
import com.epam.brest.task.domain.Star;
import com.epam.brest.task.service.GalaxyService;
import com.epam.brest.task.service.exception.BadParameterException;
import com.epam.brest.task.service.exception.TwoBadParametersException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.Set;

@Controller
@RequestMapping("/galaxies")
public class GalaxyRestController {
    @Resource
    private GalaxyService galaxyService;

    private static final Logger LOGGER = LogManager.getLogger(GalaxyRestController.class);


    public void setGalaxyService(GalaxyService galaxyService){
        this.galaxyService = galaxyService;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addGalaxy(@RequestBody Galaxy galaxy){

        //i suppose, i need to get rid of the line below, later
        galaxy.setDate(new Date(galaxy.getDate().getYear(), galaxy.getDate().getMonth(), galaxy.getDate().getDate()));
        LOGGER.debug("starts with " + galaxy);
        Long id = null;

        try {
            id = galaxyService.addGalaxy(galaxy);
            LOGGER.debug("ends with " + id);
            return new ResponseEntity(id, HttpStatus.CREATED);
        } catch (BadParameterException e) {
            LOGGER.error(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateGalaxy(@RequestBody Galaxy galaxy) {
        //and here
        galaxy.setDate(new Date(galaxy.getDate().getYear(), galaxy.getDate().getMonth(), galaxy.getDate().getDate()));
        LOGGER.debug("starts with " + galaxy);

        try {
            galaxyService.updateGalaxy(galaxy);
            LOGGER.debug("ends");
            return new ResponseEntity("Star was updated -> " + galaxy, HttpStatus.ACCEPTED);
        } catch (BadParameterException e) {
            LOGGER.error(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity("Star wasn't updated -> " + galaxy, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeGalaxy(@PathVariable Long id) {

        LOGGER.debug("starts with " + id);

        try {
            galaxyService.removeGalaxy(id);
            LOGGER.debug("ends");
            return new ResponseEntity("Star was removed -> " + id, HttpStatus.ACCEPTED);
        } catch (BadParameterException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllGalaxies() {

        LOGGER.debug("starts");

        Set<Galaxy> galaxies = galaxyService.getAllGalaxies();
        LOGGER.debug("ends with -> " + galaxies);
        return new ResponseEntity(galaxies, HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping(value = "/galaxyById/{id}", method = RequestMethod.GET)
    public ResponseEntity getGalaxyById(@PathVariable Long id) {

        LOGGER.debug("starts with -> " + id);

        try {
            Galaxy galaxy = galaxyService.getGalaxyById(id);
            LOGGER.debug("ends with -> " + galaxy);
            return new ResponseEntity(galaxy, HttpStatus.OK);
        } catch (BadParameterException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/galaxyByName/{name}", method = RequestMethod.GET)
    public ResponseEntity getGalaxyByName(@PathVariable String name) {

        LOGGER.debug("starts with -> " + name);

        try {
            Galaxy galaxy = galaxyService.getGalaxyByName(name);
            LOGGER.debug("ends with -> " + galaxy);
            return new ResponseEntity(galaxy, HttpStatus.OK);
        } catch (BadParameterException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/galaxiesByDistanceAndFlag/{distance}_{flag}", method = RequestMethod.GET)
    public ResponseEntity getGalaxiesByDistance(@PathVariable Long distance, @PathVariable Boolean flag){

        LOGGER.debug("starts with -> " + distance + " and " + flag);

        try {
            Set<Galaxy> stars = galaxyService.getGalaxiesByDistance(distance, flag);
            LOGGER.debug("ends with -> " + stars);
            return new ResponseEntity(stars, HttpStatus.OK);
        } catch (TwoBadParametersException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/galaxiesByTwoDistance/{lowBorder}_{topBorder}", method = RequestMethod.GET)
    public ResponseEntity getGalaxiesByDistance(@PathVariable Long lowBorder, @PathVariable Long topBorder){

        LOGGER.debug("starts with -> " + lowBorder + " and " + topBorder);

        try {
            Set<Galaxy> stars = galaxyService.getGalaxiesByDistance(lowBorder, topBorder);
            LOGGER.debug("ends with -> " + stars);
            return new ResponseEntity(stars, HttpStatus.OK);
        } catch (TwoBadParametersException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/galaxiesByDate/{date}", method = RequestMethod.GET)
    public ResponseEntity getGalaxiesByDate(@PathVariable Date date){
        LOGGER.debug("starts with -> " + date);

        try {
            Set<Galaxy> galaxies = galaxyService.getGalaxiesByDate(date);
            LOGGER.debug("ends with -> " + galaxies);
            return new ResponseEntity(galaxies, HttpStatus.OK);
        } catch (TwoBadParametersException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/galaxiesByDateAndFlag/{date}_{flag}", method = RequestMethod.GET)
    public ResponseEntity getGalaxiesByDate(@PathVariable Date date, @PathVariable Boolean flag){
        LOGGER.debug("starts with -> " + date + " and " + flag);

        try {
            Set<Galaxy> galaxies = galaxyService.getGalaxiesByDate(date, flag);
            LOGGER.debug("ends with -> " + galaxies);
            return new ResponseEntity(galaxies, HttpStatus.OK);
        } catch (TwoBadParametersException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/galaxiesByTwoDate/{lowBorder}_{topBorder}", method = RequestMethod.GET)
    public ResponseEntity getGalaxiesByDate(@PathVariable Date lowBorder, @PathVariable Date topBorder) {
        LOGGER.debug("starts with -> " + lowBorder + " and " + topBorder);

        try {
            Set<Galaxy> galaxies = galaxyService.getGalaxiesByDate(lowBorder, topBorder);
            LOGGER.debug("ends with -> " + galaxies);
            return new ResponseEntity(galaxies, HttpStatus.OK);
        } catch (TwoBadParametersException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }
}