package com.epam.brest.task.dao;

import com.epam.brest.task.dao.GalaxyDao;
import com.epam.brest.task.domain.Galaxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.Set;

import static org.junit.Assert.*;
import static org.springframework.util.CollectionUtils.isEmpty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-dao-test.xml"})
public class GalaxyDaoImplTest {

    @Autowired
    private GalaxyDao galaxyDao;

    private static final Long GALAXY_ID_TO_UPDATE = 1L;
    private static final Long GALAXY_ID_TO_ADD = 100L;
    private static final Long GALAXY_ID_TO_REMOVE = 0L;
    private static final Long GALAXY_ID_TO_SELECT = 2L;
    private static final Long GALAXY_DISTANCE_TO_SELECT = 2L;
    private static final Date GALAXY_DATE_TO_SELECT = new Date(2014 - 1900, 4, 2);
    private static final String GALAXY_NAME_TO_SELECT = "G2";
    private static final String NAME = "G";
    private static final Long DISTANCE_TO_ADD = 3000L;
    private static final Long DISTANCE_TO_UPDATE = 2876L;
    private static final Long DISTANCE_TO_SELECT = 2L;
    private static final Date DATE_TO_ADD = new Date(2014 - 1900, 5, 5);
    private static final Date NEW_DATE_TO_UPDATE = new Date(1993 - 1900, 1, 1);

    @Test
    public void testAddGalaxy(){
        Set<Galaxy> galaxies = galaxyDao.getAllGalaxies();

        int sizeBefore = galaxies.size();
        Galaxy galaxy = new Galaxy(GALAXY_ID_TO_ADD, NAME, DISTANCE_TO_ADD, DATE_TO_ADD);

        Long id = galaxyDao.addGalaxy(galaxy);
        assertEquals(id, GALAXY_ID_TO_ADD);

        galaxies = galaxyDao.getAllGalaxies();
        assertEquals(sizeBefore, galaxies.size() - 1);
    }

    @Test
    public void testUpdateGalaxy(){
        Galaxy galaxy = new Galaxy(GALAXY_ID_TO_UPDATE, NAME + GALAXY_ID_TO_UPDATE, DISTANCE_TO_UPDATE, NEW_DATE_TO_UPDATE);

        galaxyDao.updateGalaxy(galaxy);

        Galaxy updatedGalaxy = galaxyDao.getGalaxyById(GALAXY_ID_TO_UPDATE);

        assertEquals(updatedGalaxy.getName(), NAME + GALAXY_ID_TO_UPDATE);
        assertEquals(updatedGalaxy.getDistance(), DISTANCE_TO_UPDATE);
        assertEquals(galaxy.getDate(), NEW_DATE_TO_UPDATE);
    }

    @Test
    public void testRemoveGalaxy(){
        int sizeBefore = galaxyDao.getAllGalaxies().size();

        galaxyDao.removeGalaxy(GALAXY_ID_TO_REMOVE);

        assertEquals(galaxyDao.getAllGalaxies().size(), sizeBefore - 1);
    }

    @Test
    public void testGetGalaxyById(){
        Galaxy galaxy = galaxyDao.getGalaxyById(GALAXY_ID_TO_SELECT);
        assertNotNull(galaxy);
        assertEquals(galaxy.getName(), GALAXY_NAME_TO_SELECT);
        assertEquals(galaxy.getDistance(), GALAXY_DISTANCE_TO_SELECT);
        assertEquals(galaxy.getDate(), GALAXY_DATE_TO_SELECT);
    }

    @Test
    public void testGetGalaxiesByDate(){
        Set<Galaxy> galaxies = galaxyDao.getGalaxiesByDate(new Date(2014 - 1900, 4, 3));
        assertNotNull(galaxies);
        assertFalse(isEmpty(galaxies));
    }

    @Test
    public void testGetGalaxyByDateWithSecondBoolParameter(){
        Set<Galaxy> galaxies = galaxyDao.getGalaxiesByDate(GALAXY_DATE_TO_SELECT, true);
        assertNotNull(galaxies);
        assertFalse(isEmpty(galaxies));

        galaxies = galaxyDao.getGalaxiesByDate(GALAXY_DATE_TO_SELECT, false);
        assertNotNull(galaxies);
        assertFalse(isEmpty(galaxies));
    }

    @Test
    public void testGetGalaxyByDatePeriod(){
        Set<Galaxy> galaxies = galaxyDao.getGalaxiesByDate(new Date(2014 - 1900, 4, 1), new Date(2014 - 1900, 4, 3));
        assertNotNull(galaxies);
        assertFalse(isEmpty(galaxies));
    }

    @Test
    public void testGetGalaxyByName(){
        Galaxy galaxy = galaxyDao.getGalaxyByName("G2");
        assertNotNull(galaxy);
        assertEquals(GALAXY_NAME_TO_SELECT, galaxy.getName());
        assertEquals(GALAXY_ID_TO_SELECT, galaxy.getGalaxyId());
        assertEquals(GALAXY_DATE_TO_SELECT, galaxy.getDate());
        assertEquals(DISTANCE_TO_SELECT, galaxy.getDistance());
    }

    @Test
    public void testGetGalaxiesByDistance(){
        Set<Galaxy> galaxies = galaxyDao.getGalaxiesByDistance(DISTANCE_TO_SELECT, true);
        assertNotNull(galaxies);
        assertFalse(isEmpty(galaxies));

        galaxies = galaxyDao.getGalaxiesByDistance(DISTANCE_TO_SELECT, false);
        assertNotNull(galaxies);
        assertFalse(isEmpty(galaxies));
    }

    @Test
    public void testGetGalaxiesByDistanceInterval(){
        Set<Galaxy> galaxies = galaxyDao.getGalaxiesByDistanceInterval(DISTANCE_TO_SELECT, DISTANCE_TO_SELECT + 2L);
        assertNotNull(galaxies);
        assertFalse(isEmpty(galaxies));

    }

    @Test
    public void testGetAllGalaxies(){
        Set<Galaxy> galaxies = galaxyDao.getAllGalaxies();
        assertNotNull(galaxies);
        assertFalse(isEmpty(galaxies));

    }
}
