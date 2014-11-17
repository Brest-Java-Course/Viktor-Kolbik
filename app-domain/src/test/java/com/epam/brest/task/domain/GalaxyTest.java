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
    private static final Date DATE = new Date(2014 - 1900, 5, 5);

    private Galaxy galaxy;

    @Test
    public void testConstructors(){
        galaxy = new Galaxy();
        assertNull(galaxy.getGalaxyId());
        assertNull(galaxy.getDistance());
        assertNull(galaxy.getName());
        assertNull(galaxy.getAverageAge());
        assertNull(galaxy.getAverageMass());

        galaxy = new Galaxy(NAME, DISTANCE, DATE);
        assertNull(galaxy.getGalaxyId());
        assertEquals(galaxy.getDistance(), DISTANCE);
        assertEquals(galaxy.getName(), NAME);
        assertEquals(galaxy.getDate(), DATE);
        assertNull(galaxy.getAverageAge());
        assertNull(galaxy.getAverageMass());

        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE, DATE);
        assertEquals(galaxy.getGalaxyId(), GALAXY_ID);
        assertEquals(galaxy.getDistance(), DISTANCE);
        assertEquals(galaxy.getName(), NAME);
        assertNull(galaxy.getAverageAge());
        assertNull(galaxy.getAverageMass());

        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE, DATE, AVERAGE_AGE, AVERAGE_MASS);
        assertEquals(galaxy.getGalaxyId(), GALAXY_ID);
        assertEquals(galaxy.getDistance(), DISTANCE);
        assertEquals(galaxy.getName(), NAME);
        assertEquals(galaxy.getAverageAge(), AVERAGE_AGE);
        assertEquals(galaxy.getAverageMass(), AVERAGE_MASS, 0.01);
    }

    @Test
    public void testGettersSetters(){
        galaxy = new Galaxy();

        galaxy.setGalaxyId(GALAXY_ID);
        galaxy.setName(NAME);
        galaxy.setDistance(DISTANCE);
        galaxy.setAverageAge(AVERAGE_AGE);
        galaxy.setAverageMass(AVERAGE_MASS);
        galaxy.setDate(DATE);

        assertEquals(galaxy.getGalaxyId(), GALAXY_ID);
        assertEquals(galaxy.getName(), NAME);
        assertEquals(galaxy.getDistance(), DISTANCE);
        assertEquals(galaxy.getAverageAge(), AVERAGE_AGE);
        assertEquals(galaxy.getAverageMass(), AVERAGE_MASS, 0.01);
        assertEquals(galaxy.getDate(), DATE);
    }

    @Test
    public void testToString() throws Exception {
        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE, DATE, AVERAGE_AGE, AVERAGE_MASS);
        assertEquals(galaxy.toString(), "Galaxy{galaxyId=1, " +
                "name='MG55', distance=1000000, averageAge=9999999, averageMass=9.5}");
    }

    @Test
    public void testEqualsTheSameGalaxy() throws Exception {
        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE, DATE);

        assertTrue(galaxy.equals(galaxy));
    }

    @Test
    public void testHashCode() throws Exception {
        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE, DATE, AVERAGE_AGE, AVERAGE_MASS);
        Galaxy galaxy2 = new Galaxy(GALAXY_ID, NAME, DISTANCE, DATE, AVERAGE_AGE, AVERAGE_MASS);

        if (galaxy.equals(galaxy2)) {
            assertEquals(galaxy.hashCode(), galaxy2.hashCode());
        } else {
            assertFalse(true);
        }
    }

    @Theory
    public void testEquals(final Object testData[]) throws Exception {
        galaxy = new Galaxy(GALAXY_ID, NAME, DISTANCE, DATE);

        assertEquals(galaxy.equals(testData[0]), testData[1]);
    }

    @DataPoints
    public static Object[][] testData = new Object[][]{
            {null, false},
            {new Object(), false},
            {new Galaxy(), false},
            {new Galaxy(NAME, DISTANCE, DATE), false},
            {new Galaxy(GALAXY_ID, NAME, DISTANCE, DATE), true},
            {new Galaxy(GALAXY_ID, NAME, DISTANCE, DATE, AVERAGE_AGE, AVERAGE_MASS), false},
    };


}