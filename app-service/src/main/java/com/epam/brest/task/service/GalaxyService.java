package com.epam.brest.task.service;

import com.epam.brest.task.domain.Galaxy;

import java.sql.Date;
import java.util.Set;

/**
 * Created by simpson on 19/11/14.
 */
public interface GalaxyService {
    /**
     * Adds a galaxy without stars
     * @param galaxy the Galaxy we need to add
     * @return the Long that is id of new galaxy
     */
    public Long addGalaxy(final Galaxy galaxy);

    /**
     * updates only a galaxy
     * @param galaxy the Galaxy we need to get parameters
     *               it must have id field
     */
    public void updateGalaxy(final Galaxy galaxy);

    /**
     * removes only a galaxy
     * @param id the Long that is id of a galaxy we need to remove
     */
    public void removeGalaxy(final Long id);

    /**
     * gets a galaxy by id
     * @param id the Long that is id of a galaxy we need to get
     * @return the Galaxy for id
     */
    public Galaxy getGalaxyById(final Long id);

    /**
     * gets a galaxy by a name
     * @param name the String that is a name of a galaxy we need to get
     * @return the Galaxy for a name
     */
    public Galaxy getGalaxyByName(final String name);

    /**
     * gets all galaxies in db
     * @return the Set that stores all galaxies
     */
    public Set<Galaxy> getAllGalaxies();

    /**
     * gets galaxies that were discovered on that date
     * @param date the Date that is a discovery date
     * @return the Set that stores galaxies for date
     */
    public Set<Galaxy> getGalaxiesByDate(final Date date);

    /**
     * gets galaxies that were discovered earlier or later a date
     * @param date the Date that is boundary date
     * @param flag the Boolean that shows what diapason we need to get
     *             true means to look for earlier discoveries
     *             false means to look for later discoveries
     * @return the Set that stores galaxies for date diapason
     */
    public Set<Galaxy> getGalaxiesByDate(final Date date, final Boolean flag);

    /**
     * gets galaxies that were discovered on date diapason
     * @param lowBorder the Date that is low border of diapason
     * @param topBorder the Date that is top border of diapason
     * @return the Set that stores galaxies for date diapason
     */
    public Set<Galaxy> getGalaxiesByDate(final Date lowBorder, final Date topBorder);

    /**
     * gets galaxies that are closer or farther than distance
     * @param distance the Long that is boundary distance
     * @param flag the Boolean that shows what diapason we need to get
     *             true means to look for closer than the distance parameter
     *             false means to look for farther than the distance parameter
     * @return the Set that stores galaxies for distance diapason
     */
    public Set<Galaxy> getGalaxiesByDistance(final Long distance, final Boolean flag);

    /**
     * gets galaxies that are in the distance diapason
     * @param lowBorder the Long that is a low border of a diapason
     * @param topBorder the Long that is a top border of the diapason
     * @return the Set that stores galaxies for distance diapason
     */
    public Set<Galaxy> getGalaxiesByDistance(final Long lowBorder, final Long topBorder);
}
