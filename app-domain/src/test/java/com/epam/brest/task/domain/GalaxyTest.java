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
    private static final Long DISTANCE = 1000000L;
    private static final Long AVERAGE_AGE = 9999999L;
    private static final Double AVERAGE_MASS = 9.5;
    private static final Star STAR1 = new Star(1L, "first galaxy", 1L, 1.1, new Date(114, 5, 9));
    private static final Star STAR2 = new Star(2L, "second galaxy", 2L, 2.2, new Date(115, 1, 1));

    private Galaxy galaxy;

    @Test
    public void testConstructors(){
        galaxy = new Galaxy();
        assertNull(galaxy.getGalaxyId());
        assertNull(galaxy.getDistance());
        assertNull(galaxy.getName());
        assertNull(galaxy.getAverageAge());

        galaxy = new Galaxy(NAME, DISTANCE);
        assertNull(galaxy.getGalaxyId());
        assertNotNull(galaxy.getDistance());
        assertNotNull(galaxy.getName());

        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE);
        assertNotNull(galaxy.getGalaxyId());
        assertNotNull(galaxy.getDistance());
        assertNotNull(galaxy.getName());
    }

    @Test
    public void testGettersSetters(){
        galaxy = new Galaxy();

        galaxy.setGalaxyId(GALAXY_ID);
        galaxy.setName(NAME);
        galaxy.setDistance(DISTANCE);

        assertEquals(galaxy.getGalaxyId(), GALAXY_ID);
        assertEquals(galaxy.getName(), NAME);
        assertEquals(galaxy.getDistance(), DISTANCE);
    }

    @Test
    public void testToString() throws Exception {
        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE);
        assertEquals(galaxy.toString(), "Galaxy{galaxyId=1, name='MG55', distance=1000000, averageAge=null, averageMass=null}");
    }

    @Test
    public void testEqualsTheSameGalaxy() throws Exception {
        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE);

        assertTrue(galaxy.equals(galaxy));
    }

    @Test
    public void testHashCode() throws Exception {
        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE);
        Galaxy galaxy2 = new Galaxy(GALAXY_ID, NAME, DISTANCE);

        if (galaxy.equals(galaxy2)) {
            assertEquals(galaxy.hashCode(), galaxy2.hashCode());
        } else {
            assertFalse(true);
        }
    }

    @Theory
    public void testEquals(final Object testData[]) throws Exception {
        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE);

        assertEquals(galaxy.equals(testData[0]), testData[1]);
    }

    @DataPoints
    public static Object[][] testData = new Object[][]{
            {null, false},
            {new Object(), false},
            {new Galaxy(), false},
            {new Galaxy(NAME, DISTANCE), false},
            {new Galaxy(GALAXY_ID, NAME, DISTANCE), true},
    };


}