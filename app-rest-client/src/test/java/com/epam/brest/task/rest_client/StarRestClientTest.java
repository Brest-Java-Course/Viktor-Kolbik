package com.epam.brest.task.rest_client;

import com.epam.brest.task.domain.Star;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.Set;

import static org.junit.Assert.*;

@Ignore                    //tests works with server
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-rest-client-test.xml"})
public class StarRestClientTest {
    @Autowired
    private StarRestClient starRestClient;

    private static final String NAME_TO_ADD = "S";
    private static final Long AGE_TO_ADD = 3000L;
    private static final Long MASS_TO_ADD = 51L;
    private static final Date DATE_TO_ADD = new Date(2014 - 1900, 5, 5);
    private static final Long AGE_TO_UPDATE = 1036L;
    private static final Long MASS_TO_UPDATE = 16L;
    private static final String NAME_TO_UPDATE = "Updated name";
    private static final Date DATE_TO_UPDATE = new Date(1993 - 1900, 1, 1);
    private static final Long GALAXY_ID = 5L;
    private static final Long ID_TO_SELECT = 7L;

    @Test
    public void testAddUpdateRemoveStar(){
        int sizeBefore = starRestClient.getAllStars().size();

        Star star = new Star(null, NAME_TO_ADD, AGE_TO_ADD, MASS_TO_ADD, DATE_TO_ADD, GALAXY_ID);
        Long id = starRestClient.addStar(star);

        int sizeAfter = starRestClient.getAllStars().size();
        assertEquals(sizeBefore + 1, sizeAfter);

        star = new Star(id, NAME_TO_UPDATE, AGE_TO_UPDATE, MASS_TO_UPDATE, DATE_TO_UPDATE, GALAXY_ID);
        starRestClient.updateStar(star);
        Star updatedStar = starRestClient.getStarById(id);
        assertTrue(star.equals(updatedStar));

        starRestClient.removeStar(id);
        sizeAfter = starRestClient.getAllStars().size();
        assertEquals(sizeBefore, sizeAfter);
    }

    @Test
    public void testGetAllStars(){
        Set<Star> stars = starRestClient.getAllStars();

        assertNotNull(stars);
        assertFalse(stars.isEmpty());
    }

    @Test
    public void testGetStarById(){
        Star star = starRestClient.getStarById(ID_TO_SELECT);

        assertNotNull(star);
    }

}
