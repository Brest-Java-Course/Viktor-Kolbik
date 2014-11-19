package com.brest.epam.task.dao;

import com.epam.brest.task.dao.GalaxyDao;
import com.epam.brest.task.dao.StarDao;
import com.epam.brest.task.domain.Galaxy;
import com.epam.brest.task.domain.Star;
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
public class StarDaoImplTest {
    @Autowired
    private StarDao starDao;

    private static final String NAME_TO_ADD = "Added name";
    private static final Long ID_TO_ADD = 100L;
    private static final Long AGE_TO_ADD = 3000L;
    private static final Double MASS_TO_ADD = 51.5;
    private static final Date DATE_TO_ADD = new Date(2014 - 1900, 5, 5);
    private static final Long ID_TO_UPDATE = 1L;
    private static final Long AGE_TO_UPDATE = 1036L;
    private static final Double MASS_TO_UPDATE = 16.3;
    private static final String NAME_TO_UPDATE = "Updated name";
    private static final Date DATE_TO_UPDATE = new Date(1993 - 1900, 1, 1);
    private static final Long GALAXY_ID_TO_UPDATE = 0L;
    private static final Long GALAXY_ID = 2L;
    private static final Long ID_TO_REMOVE = 4L;
    private static final Date DATE_TO_SELECT = new Date(2014 - 1900, 0, 5);
    private static final Long AGE_TO_SELECT = 4000L;
    private static final String NAME_TO_SELECT = "S2";
    private static final Double MASS_TO_SELECT = 2.3;
    private static final Long ID_TO_SELECT = 2L;

    @Test
    public void testAddStar() throws Exception {
        Set<Star> stars = starDao.getAllStars();

        int sizeBefore = stars.size();
        Star star = new Star(ID_TO_ADD, NAME_TO_ADD, AGE_TO_ADD, MASS_TO_ADD, DATE_TO_ADD, GALAXY_ID);

        Long id = starDao.addStar(star);
        assertEquals(id, ID_TO_ADD);

        stars = starDao.getAllStars();
        assertEquals(sizeBefore, stars.size() - 1);
    }

    @Test
    public void testUpdateStar() throws Exception {
        Star star = new Star(ID_TO_UPDATE, NAME_TO_UPDATE, AGE_TO_UPDATE, MASS_TO_UPDATE, DATE_TO_UPDATE, GALAXY_ID_TO_UPDATE);

        starDao.updateStar(star);

        Star updatedStar = starDao.getStarById(ID_TO_UPDATE);

        assertEquals(updatedStar.getName(), NAME_TO_UPDATE);
        assertEquals(updatedStar.getAge(), AGE_TO_UPDATE);
        assertEquals(updatedStar.getMass(), MASS_TO_UPDATE);
        assertEquals(updatedStar.getDate(), DATE_TO_UPDATE);
        assertEquals(updatedStar.getGalaxyId(), GALAXY_ID_TO_UPDATE);
    }

    @Test
    public void testRemoveStar() throws Exception {
        int sizeBefore = starDao.getAllStars().size();

        starDao.removeStar(ID_TO_REMOVE);

        assertEquals(sizeBefore - 1, starDao.getAllStars().size());
    }

    @Test
    public void testRemoveStarsByGalaxyId() throws Exception {
        int sizeBefore = starDao.getAllStars().size();

        starDao.removeStarsByGalaxyId(GALAXY_ID_TO_UPDATE);
        int sizeAfter = starDao.getAllStars().size();
        assertTrue((sizeBefore - sizeAfter > 0) && (sizeBefore - sizeAfter < sizeBefore));

    }

    @Test
    public void testGetStarById() throws Exception {
        Star star = starDao.getStarById(ID_TO_SELECT);
        assertNotNull(star);
    }

    @Test
    public void testGetStarByName() throws Exception {
        Star star = starDao.getStarByName(NAME_TO_SELECT);
        assertNotNull(star);
    }

    @Test
    public void testGetAllStars() throws Exception {
        Set<Star> stars = starDao.getAllStars();
        assertNotNull(stars);
        assertFalse(isEmpty(stars));
    }

    @Test
    public void testGetStarsByGalaxyId() throws Exception {
        Set<Star> stars = starDao.getStarsByGalaxyId(GALAXY_ID);
        assertNotNull(stars);
        assertFalse(isEmpty(stars));
    }

    @Test
    public void testGetStarsByAgeWithBoolParameter() throws Exception {
        Set<Star> stars = starDao.getStarsByAge(AGE_TO_SELECT, true);
        assertNotNull(stars);
        assertFalse(isEmpty(stars));

        stars = starDao.getStarsByAge(AGE_TO_SELECT, false);
        assertNotNull(stars);
        assertFalse(isEmpty(stars));
    }

    @Test
    public void testGetStarsByDiapasonAge() throws Exception {
        Set<Star> stars = starDao.getStarsByAge(AGE_TO_SELECT - 1000,  AGE_TO_SELECT + 1000);

        assertNotNull(stars);
        assertFalse(isEmpty(stars));

    }

    @Test
    public void testGetStarsByMassWithBoolParameter() throws Exception {
        Set<Star> stars = starDao.getStarsByMass(MASS_TO_SELECT, true);

        assertNotNull(stars);
        assertFalse(isEmpty(stars));

        stars = starDao.getStarsByMass(MASS_TO_SELECT, false);

        assertNotNull(stars);
        assertFalse(isEmpty(stars));
    }

    @Test
    public void testGetStarsByMassDiapason() throws Exception {
        Set<Star> stars = starDao.getStarsByMass(MASS_TO_SELECT - 10, MASS_TO_SELECT + 10);

        assertNotNull(stars);
        assertFalse(isEmpty(stars));
    }

    @Test
    public void testGetStarsByDate() throws Exception {
        Set<Star> stars = starDao.getStarsByDate(DATE_TO_SELECT);

        assertNotNull(stars);
        assertFalse(isEmpty(stars));

    }

    @Test
    public void testGetStarsByDateWithBoolParameter() throws Exception {
        Set<Star> stars = starDao.getStarsByDate(DATE_TO_SELECT, true);

        assertNotNull(stars);
        assertFalse(isEmpty(stars));

        stars = starDao.getStarsByDate(DATE_TO_SELECT, false);

        assertNotNull(stars);
        assertFalse(isEmpty(stars));
    }

    @Test
    public void testGetStarsByDateDiapason() throws Exception {
        Set<Star> stars = starDao.getStarsByDate(new Date(2014 - 1900, 0, 2), new Date(2014 - 1900, 0, 4));

        assertNotNull(stars);
        assertFalse(isEmpty(stars));
    }
}