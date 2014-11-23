package com.epam.brest.task.service;

import com.epam.brest.task.dao.GalaxyDao;
import com.epam.brest.task.dao.StarDao;
import com.epam.brest.task.domain.Galaxy;
import com.epam.brest.task.service.exception.BadParameterException;
import com.epam.brest.task.service.exception.TwoBadParametersException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.sql.Date;
import java.util.Set;

public class GalaxyServiceImpl implements GalaxyService {

    @Autowired
    private GalaxyDao galaxyDao;

    @Autowired
    private StarDao starDao;

    private static final String OCCUPIED_LOGIN_MSG = "User with such login has already existed!";
    private static final String BAD_PARAMETER_MSG = "Bad parameters exception occurred. Wrong or null parameters were passed";

    private static final Logger LOGGER = LogManager.getLogger(GalaxyService.class);

    @Override
    public Long addGalaxy(final Galaxy galaxy) {
        LOGGER.debug("starts with " + galaxy);

        try {
            Assert.notNull(galaxy);
            Assert.isNull(galaxy.getGalaxyId());
            Assert.isTrue(galaxy.getName() != null && !galaxy.getName().trim().equals(""));
            Assert.notNull(galaxy.getDistance());
            Assert.isTrue(galaxy.getDistance() > 0);
            Assert.notNull(galaxy.getDate());
            Assert.isNull(galaxy.getAverageAge());
            Assert.isNull(galaxy.getAverageMass());
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + galaxy);
            throw new BadParameterException(BAD_PARAMETER_MSG, galaxy);
        }

        try {
            galaxyDao.getGalaxyByName(galaxy.getName());
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + galaxy);
            throw new BadParameterException(OCCUPIED_LOGIN_MSG, galaxy);
        } catch (EmptyResultDataAccessException e) {
            Long id = galaxyDao.addGalaxy(galaxy);
            LOGGER.debug("ends with " + id);
            return id;
        }
    }

    @Override
    public void updateGalaxy(final Galaxy galaxy) {
        LOGGER.debug("starts with " + galaxy);

        try {
            Assert.notNull(galaxy);
            Assert.notNull(galaxy.getGalaxyId());
            Assert.isTrue(galaxy.getName() != null && !galaxy.getName().trim().equals(""));
            Assert.isTrue(galaxy.getDistance() != null && galaxy.getDistance() > 0);
            Assert.notNull(galaxy.getDate());
            Assert.isNull(galaxy.getAverageAge());
            Assert.isNull(galaxy.getAverageMass());
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + galaxy);
            throw new BadParameterException(BAD_PARAMETER_MSG, galaxy);
        }

        try {
            galaxyDao.getGalaxyByName(galaxy.getName());

            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + galaxy);
            throw new BadParameterException(BAD_PARAMETER_MSG, galaxy);
        } catch (EmptyResultDataAccessException e) {
            try {
                galaxyDao.getGalaxyById(galaxy.getGalaxyId());
                galaxyDao.updateGalaxy(galaxy);
                LOGGER.debug("ends");
            } catch (EmptyResultDataAccessException e2) {
                LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + galaxy);
                throw new BadParameterException(BAD_PARAMETER_MSG, galaxy);
            }
        }
    }

    @Override
    @Transactional
    public void removeGalaxy(final Long id) {
        LOGGER.debug("starts with " + id);

        try {
            Assert.notNull(id);
            Assert.isTrue(id >= 0);

            starDao.removeStarsByGalaxyId(id);
            galaxyDao.removeGalaxy(id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + id);
            throw new BadParameterException(BAD_PARAMETER_MSG, id);
        }

        LOGGER.debug("ends");
    }

    @Override
    public Galaxy getGalaxyById(final Long id) {
        try {
            Assert.notNull(id);
            Assert.isTrue(id >= 0);

            return galaxyDao.getGalaxyById(id);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + id);
            throw new BadParameterException(BAD_PARAMETER_MSG, id);
        }
    }

    @Override
    public Galaxy getGalaxyByName(final String name) {
        try {
            Assert.notNull(name);
            Assert.isTrue(!name.trim().equals(""));

            return galaxyDao.getGalaxyByName(name);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + name);
            throw new BadParameterException(BAD_PARAMETER_MSG, name);
        }
    }

    @Override
    public Set<Galaxy> getAllGalaxies() {
        return galaxyDao.getAllGalaxies();
    }

    @Override
    public Set<Galaxy> getGalaxiesByDistance(final Long distance, final Boolean flag) {
        try {
            Assert.notNull(distance);
            Assert.notNull(flag);
            Assert.isTrue(distance >= 0);

            return galaxyDao.getGalaxiesByDistance(distance, flag);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + distance + "\n -> " + flag);
            throw new TwoBadParametersException(BAD_PARAMETER_MSG, distance, flag);
        }
    }

    @Override
    public Set<Galaxy> getGalaxiesByDistance(final Long lowBorder, final Long topBorder) {
        try {
            Assert.notNull(lowBorder);
            Assert.notNull(topBorder);
            Assert.isTrue(lowBorder >= 0);
            Assert.isTrue(topBorder - lowBorder > 0);

            return galaxyDao.getGalaxiesByDistanceInterval(lowBorder, topBorder);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + lowBorder + "\n -> " + topBorder);
            throw new TwoBadParametersException(BAD_PARAMETER_MSG, lowBorder, topBorder);
        }
    }

    @Override
    public Set<Galaxy> getGalaxiesByDate(Date date) {
        try {
            Assert.notNull(date);

            return galaxyDao.getGalaxiesByDate(date);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + date);
            throw new BadParameterException(BAD_PARAMETER_MSG, date);
        }
    }

    @Override
    public Set<Galaxy> getGalaxiesByDate(Date date, Boolean flag) {
        try {
            Assert.notNull(date);
            Assert.notNull(flag);

            return galaxyDao.getGalaxiesByDate(date);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + date + "\n -> " + flag);
            throw new TwoBadParametersException(BAD_PARAMETER_MSG, date, flag);
        }
    }

    @Override
    public Set<Galaxy> getGalaxiesByDate(final Date lowBorder, final Date topBorder) {
        try {
            Assert.notNull(lowBorder);
            Assert.notNull(topBorder);

            return galaxyDao.getGalaxiesByDate(lowBorder);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(BAD_PARAMETER_MSG + "\n -> " + lowBorder + "\n -> " + topBorder);
            throw new TwoBadParametersException(BAD_PARAMETER_MSG, lowBorder, topBorder);
        }
    }
}
