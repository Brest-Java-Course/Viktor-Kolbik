package com.epam.brest.task.domain;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.sql.Date;

@RunWith(Theories.class)
public class StarTest extends TestCase {

    private static final Long STAR_ID = 1L;
    private static final String NAME = "MG55";
    private static final Long AGE = 1000000L;
    private static final Long MASS = 2L;
    private static final Date DATE = new Date(2014 - 1900, 10, 15);
    private static final Long GALAXY_ID = 1L;

    private Star star;

    @Test
    public void testConstructors() throws Exception {

        star = new Star();
        assertNull(star.getAge());
        assertNull(star.getDate());
        assertNull(star.getMass());
        assertNull(star.getName());
        assertNull(star.getStarId());

        star = new Star(NAME, AGE, MASS, DATE);
        assertEquals(star.getAge(), AGE);
        assertEquals(star.getDate(), DATE);
        assertEquals(star.getMass(), MASS);
        assertEquals(star.getName(), NAME);

        star = new Star(STAR_ID, NAME, AGE, MASS, DATE, GALAXY_ID);
        assertEquals(star.getStarId(), STAR_ID);
        assertEquals(star.getAge(), AGE);
        assertEquals(star.getDate(), DATE);
        assertEquals(star.getMass(), MASS);
        assertEquals(star.getName(), NAME);
    }

    @Test
    public void testGettersSetters() throws Exception {

        star = new Star();

        star.setStarId(STAR_ID);
        star.setName(NAME);
        star.setAge(AGE);
        star.setMass(MASS);
        star.setDate(DATE);

        assertEquals(star.getStarId(), STAR_ID);
        assertEquals(star.getAge(), AGE);
        assertEquals(star.getDate(), DATE);
        assertEquals(star.getMass(), MASS);
        assertEquals(star.getName(), NAME);
    }

    @Test
    public void testToString() throws Exception {
        star = new Star(STAR_ID, NAME, AGE, MASS, DATE, GALAXY_ID);
        assertEquals(star.toString(), "Star{starId=1, name='MG55', age=1000000," +
                " mass=2, date=2014-11-15, galaxyId=1}");
    }


    @Theory
    public void testEquals(final Object testData[]) throws Exception {
        star = new Star(STAR_ID, NAME, AGE, MASS, DATE, GALAXY_ID);

        assertEquals(star.equals(testData[0]), testData[1]);
    }

    @Test
    public void testEqualsTheSameStar() throws Exception {
        star = new Star(STAR_ID, NAME, AGE, MASS, DATE, GALAXY_ID);

        assertTrue(star.equals(star));
    }

    @Test
    public void testHashCode() throws Exception {
        star = new Star(STAR_ID, NAME, AGE, MASS, DATE, GALAXY_ID);
        Star star2 = new Star(STAR_ID, NAME, AGE, MASS, DATE, GALAXY_ID);

        if (star.equals(star2)) {
            assertEquals(star.hashCode(), star2.hashCode());
        } else {
            assertFalse(true);
        }
    }

    @DataPoints
    public static Object[][] testData = new Object[][]{
            {null, false},
            {new Object(), false},
            {new Star(), false},
            {new Star(NAME, AGE, MASS, DATE), false},
            {new Star(STAR_ID, NAME, AGE, MASS, DATE, GALAXY_ID), true},
            {new Star(STAR_ID, NAME, AGE, MASS + 1, DATE, GALAXY_ID), false},

    };
}