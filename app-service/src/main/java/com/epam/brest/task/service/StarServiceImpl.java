package com.epam.brest.task.service;

import com.epam.brest.task.dao.GalaxyDao;
import com.epam.brest.task.dao.StarDao;
import com.epam.brest.task.domain.Galaxy;
import com.epam.brest.task.domain.Star;
import com.epam.brest.task.service.exception.BadParameterException;
import com.epam.brest.task.service.exception.TwoBadParametersException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Date;
import java.util.Set;

@Service
public class StarServiceImpl implements StarService {
    @Autowired
    private StarDao starDao;

    @Autowired
    private GalaxyDao galaxyDao;

    private static final Logger LOGGER = LogManager.getLogger(StarServiceImpl.class);

    private static final String OCCUPIED_LOGIN_MSG = "Star with such name has already existed!";
    private static final String BAD_PARAMETER_MSG = "Bad parameters exception occurred. Wrong or null parameters were passed";

    @Override
    public Long addStar(final Star star) {

        try {
            Assert.notNull(star);
            Assert.isNull(star.getStarId());
            Assert.isTrue(star.getName() != null && !star.getName().trim().equals(""));
            Assert.notNull(star.getMass());
            Assert.isTrue(!(star.getMass() <= 0));
            Assert.isTrue(!(star.getAge() <= 100L));
            Assert.notNull(star.getDate());
        } catch(IllegalArgumentException e){
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + star);
            throw new BadParameterException(BAD_PARAMETER_MSG, star);
        }

        try{
            starDao.getStarByName(star.getName());
            LOGGER.debug(OCCUPIED_LOGIN_MSG + "\n -> " + star);
            throw new BadParameterException(OCCUPIED_LOGIN_MSG, star);
        }catch (EmptyResultDataAccessException e){
                galaxyDao.getGalaxyById(star.getGalaxyId());
                return starDao.addStar(star);
        }
    }

    @Override
    public void updateStar(final Star star) {
        try {
            Assert.notNull(star);
            Assert.notNull(star.getStarId());
            Assert.isTrue(star.getName() != null && !star.getName().trim().equals(""));
            Assert.notNull(star.getMass());
            Assert.isTrue(!(star.getMass() <= 0));
            Assert.isTrue(!(star.getAge() <= 100L));
            Assert.notNull(star.getDate());
        } catch(IllegalArgumentException e){
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + star);
            throw new BadParameterException(BAD_PARAMETER_MSG, star);
        }


            try{
                starDao.getStarsByGalaxyId(star.getGalaxyId());
                starDao.updateStar(star);
            } catch(EmptyResultDataAccessException e2){
                LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + star);
                throw new BadParameterException(OCCUPIED_LOGIN_MSG, star);
            }


    }

    @Override
    public void removeStar(final Long id) {
        try {
            Assert.notNull(id);
            Assert.isTrue(id >= 0);

            starDao.removeStar(id);
        }catch(IllegalArgumentException e){
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + id);
            throw new BadParameterException(BAD_PARAMETER_MSG, id);
        }
    }

    @Override
    public void removeStarsByGalaxyId(final Long id) {
        try {
            Assert.notNull(id);
            Assert.isTrue(id >= 0);

            starDao.removeStarsByGalaxyId(id);
        }catch(IllegalArgumentException e){
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + id);
            throw new BadParameterException(BAD_PARAMETER_MSG, id);
        }
    }

    @Override
    public Star getStarById(final Long id) {
        try {
            Assert.notNull(id);
            Assert.isTrue(id >= 0);

            return starDao.getStarById(id);
        }catch(IllegalArgumentException e){
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + id);
            throw new BadParameterException(BAD_PARAMETER_MSG, id);
        }
    }

    @Override
    public Star getStarByName(final String name) {
        try {
            Assert.notNull(name);
            Assert.isTrue(!name.trim().equals(""));
            return starDao.getStarByName(name);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + name);
            throw new BadParameterException(BAD_PARAMETER_MSG, name);
        }
    }

    @Override
    public Set<Star> getAllStars() {
        return starDao.getAllStars();
    }

    @Override
    public Set<Star> getStarsByGalaxyId(final Long id) {

        try {
            Assert.notNull(id);
            Assert.isTrue(id >= 0);
            return starDao.getStarsByGalaxyId(id);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + id);
            throw new BadParameterException(BAD_PARAMETER_MSG, id);
        }
    }

    @Override
    public Set<Star> getStarsByAge(final Long age, final Boolean flag) {
        try {
            Assert.notNull(age);
            Assert.notNull(flag);
            Assert.isTrue(age >= 100);

            return starDao.getStarsByAge(age, flag);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + age + "\n -> " + flag);
            throw new TwoBadParametersException(BAD_PARAMETER_MSG, age, flag);
        }
    }

    @Override
    public Set<Star> getStarsByAge(final Long lowBorder, final Long topBorder) {
        try {
            Assert.notNull(lowBorder);
            Assert.notNull(topBorder);
            Assert.isTrue(topBorder - lowBorder > 0);
            Assert.isTrue(topBorder < 20000000000L);
            Assert.isTrue(lowBorder > 100);

            return starDao.getStarsByAge(lowBorder, topBorder);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + lowBorder + "\n -> " + topBorder);
            throw new TwoBadParametersException(BAD_PARAMETER_MSG, lowBorder, topBorder);
        }
    }

    @Override
    public Set<Star> getStarsByMass(final Long mass, final Boolean flag) {
        try {
            Assert.notNull(mass);
            Assert.notNull(mass);
            Assert.isTrue(mass > 0);

            return starDao.getStarsByMass(mass, flag);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + mass + "\n -> " + flag);
            throw new TwoBadParametersException(BAD_PARAMETER_MSG, mass, flag);
        }
    }

    @Override
    public Set<Star> getStarsByMass(final Long lowBorder, final Long topBorder) {
        try {
            Assert.notNull(lowBorder);
            Assert.notNull(topBorder);
            Assert.isTrue(topBorder - lowBorder > 0);
            Assert.isTrue(lowBorder > 0);

            return starDao.getStarsByMass(lowBorder, topBorder);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + lowBorder + "\n -> " + topBorder);
            throw new TwoBadParametersException(BAD_PARAMETER_MSG, lowBorder, topBorder);
        }
    }

    @Override
    public Set<Star> getStarsByDate(final Date date) {
        try {
            Assert.notNull(date);
            return starDao.getStarsByDate(date);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + date);
            throw new BadParameterException(BAD_PARAMETER_MSG, date);
        }
    }

    @Override
    public Set<Star> getStarsByDate(final Date date, final Boolean flag) {
        try {
            Assert.notNull(date);
            Assert.notNull(flag);

            return starDao.getStarsByDate(date, flag);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + date + "\n -> " + flag);
            throw new TwoBadParametersException(BAD_PARAMETER_MSG, date, flag);
        }
    }

    @Override
    public Set<Star> getStarsByDate(final Date lowBorder, final Date topBorder ) {
        try {
            Assert.notNull(lowBorder);
            Assert.notNull(topBorder);

            return starDao.getStarsByDate(lowBorder, topBorder);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + lowBorder + "\n -> " + topBorder);
            throw new TwoBadParametersException(BAD_PARAMETER_MSG, lowBorder, topBorder);
        }
    }
}
