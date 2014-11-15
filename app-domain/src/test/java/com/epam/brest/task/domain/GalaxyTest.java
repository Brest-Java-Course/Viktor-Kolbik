package com.epam.brest.task.domain;

import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(Theories.class)
public class GalaxyTest extends TestCase {
    private static final Long GALAXY_ID = 1L;
    private static final String NAME = "MG55";
    private static final Long DISTANCE= 1000000L;
    private static final Set<Star> STARS = new HashSet();
    private static final Star STAR1 = new Star(1L, "first galaxy", 1L, 1.1, new Date(114, 5, 9));
    private static final Star STAR2 = new Star(2L, "second galaxy", 2L, 2.2, new Date(115, 1, 1));

    private Galaxy galaxy;

    @BeforeClass
    public static void setUpTest(){
        STARS.add(STAR1);
        STARS.add(STAR2);
    }

    @Test
    public void testConstructors(){
        galaxy = new Galaxy();
        assertNull(galaxy.getGalaxyId());
        assertNull(galaxy.getDistance());
        assertNull(galaxy.getStars());
        assertNull(galaxy.getName());

        galaxy = new Galaxy(NAME, DISTANCE);
        assertNull(galaxy.getGalaxyId());
        assertNotNull(galaxy.getDistance());
        assertNull(galaxy.getStars());
        assertNotNull(galaxy.getName());

        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE);
        assertNotNull(galaxy.getGalaxyId());
        assertNotNull(galaxy.getDistance());
        assertNull(galaxy.getStars());
        assertNotNull(galaxy.getName());

        galaxy = new Galaxy(NAME, DISTANCE, STARS);
        assertNull(galaxy.getGalaxyId());
        assertNotNull(galaxy.getDistance());
        Set<Star> set = galaxy.getStars();
        assertNotNull(set);
        assertFalse(set.add(STAR1));
        assertFalse(set.add(STAR2));

        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE, STARS);
        assertNotNull(galaxy.getGalaxyId());
        assertNotNull(galaxy.getDistance());

        set = galaxy.getStars();
        assertNotNull(set);
        assertFalse(set.add(STAR1));
        assertFalse(set.add(STAR2));
    }

    @Test
    public void testGettersSetters(){
        galaxy = new Galaxy();

        galaxy.setGalaxyId(GALAXY_ID);
        galaxy.setName(NAME);
        galaxy.setDistance(DISTANCE);
        galaxy.setStars(STARS);

        assertEquals(galaxy.getGalaxyId(), GALAXY_ID);
        assertEquals(galaxy.getName(), NAME);
        assertEquals(galaxy.getDistance(), DISTANCE);
        assertEquals(galaxy.getStars(), STARS);
    }

    @Test
    public void testToString() throws Exception {
        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE, STARS);
        assertEquals(galaxy.toString(), "Galaxy{galaxyId=1, name='MG55', distance=1000000, stars=[Star{starId=2, name='second galaxy', age=2, mass=2.2, date=2015-02-01}, Star{starId=1, name='first galaxy', age=1, mass=1.1, date=2014-06-09}]}");
    }

    @Theory
    public void testEquals(final Object testData[]) throws Exception {
        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE, STARS);

        assertEquals(galaxy.equals(testData[0]), testData[1]);
    }

    @Test
    public void testEqualsTheSameGalaxy() throws Exception {
        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE, STARS);

        assertTrue(galaxy.equals(galaxy));
    }

    @Test
    public void testHashCode() throws Exception {
        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE, STARS);
        Galaxy galaxy2 = new Galaxy(GALAXY_ID, NAME, DISTANCE, STARS);

        if (galaxy.equals(galaxy2)) {
            assertEquals(galaxy.hashCode(), galaxy2.hashCode());
        } else {
            assertFalse(true);
        }
    }

    @DataPoints
    public static Object[][] testData = new Object[][]{
            {null, false},
            {new Object(), false},
            {new Galaxy(), false},
            {new Galaxy(NAME, DISTANCE), false},
            {new Galaxy(GALAXY_ID, NAME, DISTANCE), false},
            {new Galaxy(NAME, DISTANCE, STARS), false},
            {new Galaxy(GALAXY_ID, NAME, DISTANCE, STARS), true},
            {new Galaxy(GALAXY_ID, NAME, DISTANCE, null), false},
    };


}