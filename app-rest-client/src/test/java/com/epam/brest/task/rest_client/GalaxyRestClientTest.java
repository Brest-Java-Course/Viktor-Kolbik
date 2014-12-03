package com.epam.brest.task.rest_client;

import com.epam.brest.task.domain.Galaxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-rest-client-test.xml"})
public class GalaxyRestClientTest {

    @Autowired
    private GalaxyRestClient galaxyRestClient;


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

    private static final Logger LOGGER = LogManager.getLogger(GalaxyRestClient.class);
    private static final String OCCUPIED_LOGIN_MSG = "Star with such name has already existed!";
    private static final String BAD_PARAMETER_MSG = "Bad parameters exception occurred. Wrong or null parameters were passed";
    private static final String SERVER_URL = "http://localhost:8080/universe/restGalaxy/";

    @Test
    public void testAddUpdateRemoveGalaxy() {
        Set<Galaxy> galaxies = galaxyRestClient.getAllGalaxies();

        int sizeBefore = galaxies.size();
        Galaxy galaxy = new Galaxy(null, NAME_TO_ADD, DISTANCE_TO_ADD, DATE_TO_ADD);

        Long id = galaxyRestClient.addGalaxy(galaxy);

        int sizeAfter = galaxyRestClient.getAllGalaxies().size();
        assertEquals(sizeBefore, sizeAfter - 1);

        galaxy = new Galaxy(id, NAME_TO_UPDATE, DISTANCE_TO_UPDATE, DATE_TO_ADD);
        galaxyRestClient.updateGalaxy(galaxy);
        Galaxy updatedGalaxy = galaxyRestClient.getGalaxyById(id);
        updatedGalaxy.setAverageAge(null);
        updatedGalaxy.setAverageMass(null);
        assertTrue(galaxy.equals(updatedGalaxy));

        galaxyRestClient.removeGalaxy(id);
        sizeAfter = galaxyRestClient.getAllGalaxies().size();

        assertEquals(sizeBefore, sizeAfter);
    }

    @Test
    public void testGetAllGalaxies(){
        Set<Galaxy> galaxies = galaxyRestClient.getAllGalaxies();

        assertNotNull(galaxies);
        assertFalse(galaxies.isEmpty());
    }

    @Test
    public void testGetGalaxyById(){
        Galaxy galaxy = galaxyRestClient.getGalaxyById(ID_TO_SELECT);

        assertNotNull(galaxy);
    }

}
