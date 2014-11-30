package com.epam.brest.task.service;

import com.epam.brest.task.domain.Galaxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.Set;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.springframework.util.CollectionUtils.isEmpty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-service-test.xml"})
public class GalaxyServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private GalaxyService galaxyService;

    @Autowired
    private  StarService starService;

    private static final Long ID_TO_UPDATE = 0L;
    private static final String NAME_TO_ADD = "Added name";
    private static final Long ID_TO_REMOVE = 1L;
    private static final Long ID_TO_SELECT = 2L;
    private static final Date DATE_TO_SELECT = new Date(2014 - 1900, 4, 2);
    private static final String NAME_TO_SELECT = "G2";
    private static final String NAME_TO_UPDATE = "updated name";
    private static final Long DISTANCE_TO_ADD = 3000L;
    private static final Long DISTANCE_TO_UPDATE = 2876L;
    private static final Long DISTANCE_TO_SELECT = 2L;
    private static final Date DATE_TO_ADD = new Date(2014 - 1900, 5, 5);
    private static final Date NEW_DATE_TO_UPDATE = new Date(1993 - 1900, 1, 1);
    private static final Date DATE_TO_UPDATE = new Date(2015 - 1900, 5, 5);

    @Test
    public void testAddGalaxy() throws Exception {

        Set<Galaxy> galaxies = galaxyService.getAllGalaxies();

        int sizeBefore = galaxies.size();
        Galaxy galaxy = new Galaxy(null, NAME_TO_ADD, DISTANCE_TO_ADD, DATE_TO_ADD);

        Long id = galaxyService.addGalaxy(galaxy);

        galaxies = galaxyService.getAllGalaxies();
        assertEquals(sizeBefore, galaxies.size() - 1);

    }

    @Test
    public void testUpdateGalaxy() throws Exception {
        Galaxy galaxy = new Galaxy(ID_TO_UPDATE, NAME_TO_UPDATE, DISTANCE_TO_UPDATE, DATE_TO_ADD);

        galaxyService.updateGalaxy(galaxy);

        Galaxy updatedGalaxy = galaxyService.getGalaxyById(ID_TO_UPDATE);
        updatedGalaxy.setAverageAge(null);
        updatedGalaxy.setAverageMass(null);

        assertEquals(galaxy, updatedGalaxy);
    }

    @Test
    public void testRemoveGalaxy() throws Exception {
        int starSizeBefore = starService.getAllStars().size();
        int galaxySizeBefore = galaxyService.getAllGalaxies().size();

        galaxyService.removeGalaxy(ID_TO_REMOVE);

        assertEquals(starSizeBefore, starService.getAllStars().size() + 2);
        assertEquals(galaxySizeBefore, galaxyService.getAllGalaxies().size() + 1);
    }

    @Test
    public void testGetGalaxyById() throws Exception {
        Galaxy galaxy = galaxyService.getGalaxyById(ID_TO_SELECT);

        assertNotNull(galaxy);
    }

    @Test
    public void testGetGalaxyByName() throws Exception {
        Galaxy galaxy = galaxyService.getGalaxyByName(NAME_TO_SELECT);
        assertNotNull(galaxy);
    }

    @Test
    public void testGetAllGalaxies() throws Exception {
        Set<Galaxy> galaxies = galaxyService.getAllGalaxies();
        assertNotNull(galaxies);
        assertFalse(isEmpty(galaxies));
    }

    @Test
    public void testGetGalaxiesByDistance() throws Exception {
        Set<Galaxy> galaxies = galaxyService.getGalaxiesByDistance(DISTANCE_TO_SELECT, true);
        assertNotNull(galaxies);
        assertFalse(isEmpty(galaxies));

        galaxies = galaxyService.getGalaxiesByDistance(DISTANCE_TO_SELECT, false);
        assertNotNull(galaxies);
        assertFalse(isEmpty(galaxies));
    }

    @Test
    public void testGetGalaxiesByDistanceInterval() throws Exception {
        Set<Galaxy> galaxies = galaxyService.getGalaxiesByDistance(DISTANCE_TO_SELECT - 2, DISTANCE_TO_SELECT + 2);
        assertNotNull(galaxies);
        assertFalse(isEmpty(galaxies));
    }

    @Test
    public void testGetGalaxiesByDate() throws Exception {
        Set<Galaxy> galaxies = galaxyService.getGalaxiesByDate(DATE_TO_SELECT);
        assertNotNull(galaxies);
        assertFalse(isEmpty(galaxies));

    }

    @Test
    public void testGetGalaxiesByDate1() throws Exception {
        Set<Galaxy> galaxies = galaxyService.getGalaxiesByDate(DATE_TO_SELECT, true);
        assertNotNull(galaxies);
        assertFalse(isEmpty(galaxies));

        galaxies = galaxyService.getGalaxiesByDate(DATE_TO_SELECT, false);
        assertNotNull(galaxies);
        assertFalse(isEmpty(galaxies));
    }

    @Test
    public void testGetGalaxiesByDate2() throws Exception {
        Set<Galaxy> galaxies = galaxyService.getGalaxiesByDate(DATE_TO_SELECT, new Date(2014 - 1900, 0, 9));
        assertNotNull(galaxies);
        assertFalse(isEmpty(galaxies));
    }
}