package com.epam.brest.task.service;

import com.epam.brest.task.domain.Star;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.Set;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.springframework.util.CollectionUtils.isEmpty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-service-test.xml"})
public class StarServiceImplTest {

    @Autowired
    private StarService starService;

    private static final String NAME_TO_ADD = "Added name";
    private static final Long ID_TO_ADD = 8L;
    private static final Long AGE_TO_ADD = 3000L;
    private static final Double MASS_TO_ADD = 51.5;
    private static final Date DATE_TO_ADD = new Date(2014 - 1900, 5, 5);
    private static final Long ID_TO_UPDATE = 1L;
    private static final Long AGE_TO_UPDATE = 1036L;
    private static final Double MASS_TO_UPDATE = 16.3;
    private static final String NAME_TO_UPDATE = "Updated name";
    private static final Date DATE_TO_UPDATE = new Date(1993 - 1900, 1, 1);
    private static final Long GALAXY_ID = 2L;
    private static final Long ID_TO_REMOVE = 4L;
    private static final Date DATE_TO_SELECT = new Date(2014 - 1900, 0, 5);
    private static final Long AGE_TO_SELECT = 4000L;
    private static final String NAME_TO_SELECT = "S3";
    private static final Double MASS_TO_SELECT = 2.3;
    private static final Long ID_TO_SELECT = 2L;

    @Test
    public void testAddStar() throws Exception {
        Set<Star> stars = starService.getAllStars();

        int sizeBefore = stars.size();
        Star star = new Star(null, NAME_TO_ADD, AGE_TO_UPDATE, MASS_TO_ADD, DATE_TO_ADD, GALAXY_ID);

        Long id = starService.addStar(star);

        stars = starService.getAllStars();
        assertEquals(sizeBefore, stars.size() - 1);
    }

    @Test
    public void testUpdateStar() throws Exception {
        Star star = new Star(ID_TO_UPDATE, NAME_TO_UPDATE, AGE_TO_UPDATE, MASS_TO_UPDATE, DATE_TO_UPDATE, GALAXY_ID);

        starService.updateStar(star);

        Star updatedStar = starService.getStarById(ID_TO_UPDATE);

        assertEquals(star, updatedStar);
    }

    @Test
    public void testRemoveStar() throws Exception {
        int sizeBefore = starService.getAllStars().size();
        starService.removeStar(ID_TO_REMOVE - 1);
        int sizeAfter = starService.getAllStars().size();

        assertEquals(sizeBefore, sizeAfter + 1);
    }

    @Test
    public void testRemoveStarsByGalaxyId() throws Exception {
        int sizeBefore = starService.getAllStars().size();
        starService.removeStarsByGalaxyId(ID_TO_REMOVE);
        int sizeAfter = starService.getAllStars().size();

        assertEquals(sizeBefore, sizeAfter + 1);
    }

    @Test
    public void testGetStarById() throws Exception {
        Star star = starService.getStarById(ID_TO_SELECT);
        assertNotNull(star);
    }

    @Test
    public void testGetStarByName() throws Exception {
        Star star = starService.getStarByName(NAME_TO_SELECT);
        assertNotNull(star);
    }

    @Test
    public void testGetAllStars() throws Exception {
        Set<Star> stars = starService.getAllStars();
        assertNotNull(stars);
        assertFalse(isEmpty(stars));
    }

    @Test
    public void testGetStarsByGalaxyId() throws Exception {
        Set<Star> stars = starService.getStarsByGalaxyId(GALAXY_ID);
        assertNotNull(stars);
        assertFalse(isEmpty(stars));
    }

    @Test
    public void testGetStarsByAge() throws Exception {
        Set<Star> stars = starService.getStarsByAge(AGE_TO_SELECT, true);
        assertNotNull(stars);
        assertFalse(isEmpty(stars));

        stars = starService.getStarsByAge(AGE_TO_SELECT, false);
        assertNotNull(stars);
        assertFalse(isEmpty(stars));
    }

    @Test
    public void testGetStarsByAge1() throws Exception {
        Set<Star> stars = starService.getStarsByAge(AGE_TO_SELECT - 1000, AGE_TO_SELECT + 1000);
        assertNotNull(stars);
        assertFalse(isEmpty(stars));
    }

    @Test
    public void testGetStarsByMass() throws Exception {
        Set<Star> stars = starService.getStarsByMass(MASS_TO_SELECT, true);
        assertNotNull(stars);
        assertFalse(isEmpty(stars));

        stars = starService.getStarsByMass(MASS_TO_SELECT, false);
        assertNotNull(stars);
        assertFalse(isEmpty(stars));
    }

    @Test
    public void testGetStarsByMass1() throws Exception {
        Set<Star> stars = starService.getStarsByMass(MASS_TO_SELECT - 2, MASS_TO_SELECT + 2);
        assertNotNull(stars);
        assertFalse(isEmpty(stars));
    }

    @Test
    public void testGetStarsByDate() throws Exception {
        Set<Star> stars = starService.getStarsByDate(DATE_TO_SELECT);
        assertNotNull(stars);
        assertFalse(isEmpty(stars));
        assertTrue(stars.size() == 1);
    }

    @Test
    public void testGetStarsByDate1() throws Exception {
        Set<Star> stars = starService.getStarsByDate(DATE_TO_SELECT, true);
        assertNotNull(stars);
        assertFalse(isEmpty(stars));

        stars = starService.getStarsByDate(DATE_TO_SELECT, false);
        assertNotNull(stars);
        assertFalse(isEmpty(stars));
    }

    @Test
    public void testGetStarsByDate2() throws Exception {
        Set<Star> stars = starService.getStarsByDate(DATE_TO_SELECT, new Date(2014 - 1900, 0, 9));
        assertNotNull(stars);
        assertFalse(isEmpty(stars));
    }
}