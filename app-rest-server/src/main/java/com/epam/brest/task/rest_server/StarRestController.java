package com.epam.brest.task.rest_server;

import com.epam.brest.task.domain.Star;
import com.epam.brest.task.service.StarService;
import com.epam.brest.task.service.exception.BadParameterException;
import com.epam.brest.task.service.exception.TwoBadParametersException;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Set;
import javax.annotation.Resource;

@Controller
@RequestMapping("/stars")
public class StarRestController {
    @Resource
    private StarService starService;

    public void setStarService(StarService starService) {
        this.starService = starService;
    }

    private static final Logger LOGGER = LogManager.getLogger(StarRestController.class);

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addStar(@RequestBody Star star) {

        //i suppose, i need to get rid of the line below. later
        star.setDate(new Date(star.getDate().getYear(), star.getDate().getMonth(), star.getDate().getDate()));
        LOGGER.debug("starts with " + star);
        Long id = null;

        try {
            id = starService.addStar(star);
            LOGGER.debug("ends with " + id);
            return new ResponseEntity(id, HttpStatus.CREATED);
        } catch (BadParameterException e) {
            LOGGER.error(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }

    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateStar(@RequestBody Star star) {
        //the same problem
        star.setDate(new Date(star.getDate().getYear(), star.getDate().getMonth(), star.getDate().getDate()));
        LOGGER.debug("starts with " + star);

        try {
            starService.updateStar(star);
            LOGGER.debug("ends");
            return new ResponseEntity("Star was updated -> " + star, HttpStatus.ACCEPTED);
        } catch (BadParameterException e) {
            LOGGER.error(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity("Star wasn't updated -> " + star, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeStar(@PathVariable Long id) {

        LOGGER.debug("starts with " + id);

        try {
            starService.removeStar(id);
            LOGGER.debug("ends");
            return new ResponseEntity("Star was removed -> " + id, HttpStatus.ACCEPTED);
        } catch (BadParameterException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/starById/{id}", method = RequestMethod.GET)
    public ResponseEntity getStarById(@PathVariable Long id) {

        LOGGER.debug("starts with -> " + id);

        try {
            Star star = starService.getStarById(id);
            LOGGER.debug("ends with -> " + star);
            return new ResponseEntity(star, HttpStatus.OK);
        } catch (BadParameterException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/starsByGalaxyId/{id}", method = RequestMethod.GET)
    public ResponseEntity getStarByGalaxyId(@PathVariable Long id) {

        LOGGER.debug("starts with -> " + id);

        try {
            Set<Star> stars = starService.getStarsByGalaxyId(id);
            LOGGER.debug("ends with -> " + stars);
            return new ResponseEntity(stars, HttpStatus.OK);
        } catch (BadParameterException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllStars() {

        LOGGER.debug("starts");

        Set<Star> stars = starService.getAllStars();
        LOGGER.debug("ends with -> " + stars);
        return new ResponseEntity(stars, HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping(value = "/starByName/{name}", method = RequestMethod.GET)
    public ResponseEntity getStarByName(@PathVariable String name) {

        LOGGER.debug("starts with -> " + name);

        try {
            Star star = starService.getStarByName(name);
            LOGGER.debug("ends with -> " + star);
            return new ResponseEntity(star, HttpStatus.OK);
        } catch (BadParameterException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/starsByAgeAndFlag/{age}_{flag}", method = RequestMethod.GET)
    public ResponseEntity getStarsByAge(@PathVariable Long age, @PathVariable Boolean flag){
        LOGGER.debug("starts with -> " + age + " and " + flag);

        try {
            Set<Star> stars = starService.getStarsByAge(age, flag);
            LOGGER.debug("ends with -> " + stars);
            return new ResponseEntity(stars, HttpStatus.OK);
        } catch (TwoBadParametersException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/starsByTwoAge/{lowBorder}_{topBorder}", method = RequestMethod.GET)
    public ResponseEntity getStarsByAge(@PathVariable Long lowBorder, @PathVariable Long topBorder){
        LOGGER.debug("starts with -> " + lowBorder + " and " + topBorder);

        try {
            Set<Star> stars = starService.getStarsByAge(lowBorder, topBorder);
            LOGGER.debug("ends with -> " + stars);
            return new ResponseEntity(stars, HttpStatus.OK);
        } catch (TwoBadParametersException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/starsByMassAndFlag/{mass}_{flag}", method = RequestMethod.GET)
    public ResponseEntity getStarsByMass(@PathVariable Double mass, @PathVariable Boolean flag){
        LOGGER.debug("starts with -> " + mass + " and " + flag);

        try {
            Set<Star> stars = starService.getStarsByMass(mass, flag);
            LOGGER.debug("ends with -> " + stars);
            return new ResponseEntity(stars, HttpStatus.OK);
        } catch (TwoBadParametersException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/starsByTwoMass/{lowBorder}_{topBorder}", method = RequestMethod.GET)
    public ResponseEntity getStarsByMass(@PathVariable Double lowBorder, @PathVariable Double topBorder){
        LOGGER.debug("starts with -> " + lowBorder/100.0 + " and " + topBorder/100.0);

        try {
            Set<Star> stars = starService.getStarsByMass(lowBorder/100.0, topBorder/100.0);
            LOGGER.debug("ends with -> " + stars);
            return new ResponseEntity(stars, HttpStatus.OK);
        } catch (TwoBadParametersException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/starsByDate/{date}", method = RequestMethod.GET)
    public ResponseEntity getStarsByDate(@PathVariable Date date){
        LOGGER.debug("starts with -> " + date);

        try {
            Set<Star> stars = starService.getStarsByDate(date);
            LOGGER.debug("ends with -> " + stars);
            return new ResponseEntity(stars, HttpStatus.OK);
        } catch (TwoBadParametersException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/starsByDateAndFlag/{date}_{flag}", method = RequestMethod.GET)
    public ResponseEntity getStarsByDate(@PathVariable Date date, @PathVariable Boolean flag){
        LOGGER.debug("starts with -> " + date + " and " + flag);

        try {
            Set<Star> stars = starService.getStarsByDate(date, flag);
            LOGGER.debug("ends with -> " + stars);
            return new ResponseEntity(stars, HttpStatus.OK);
        } catch (TwoBadParametersException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/starsByTwoDate/{lowBorder}_{topBorder}", method = RequestMethod.GET)
    public ResponseEntity getStarsByDate(@PathVariable Date lowBorder, @PathVariable Date topBorder) {
        LOGGER.debug("starts with -> " + lowBorder + " and " + topBorder);

        try {
            Set<Star> stars = starService.getStarsByDate(lowBorder, topBorder);
            LOGGER.debug("ends with -> " + stars);
            return new ResponseEntity(stars, HttpStatus.OK);
        } catch (TwoBadParametersException e) {
            LOGGER.debug(e.getMessage() + e.getObjectOfException());
            return new ResponseEntity(e.getMessage() + e.getObjectOfException(), HttpStatus.BAD_REQUEST);
        }
    }
}