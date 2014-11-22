package com.epam.brest.task.service;

import com.epam.brest.task.domain.Star;

import java.sql.Date;
import java.util.Set;

/**
 * Created by simpson on 19/11/14.
 */
public interface StarService {
    /**
     * Adds a star in db
     * @param star the Star we need to add
     * @return the Long that is id of new star
     */
    public Long addStar(final Star star);

    /**
     * updates a star
     * @param star the Star we need to get parameters
     *             it must have id field
     */
    public void updateStar(final Star star);
    /**
     * removes a star by id
     * @param id the Long that is id of star we need to remove
     */
    public void removeStar(final Long id);
    /**
     * removes a star
     * @param id the Long that is id of galaxy
     *           from where we need to remove all stars
     */
    public void removeStarsByGalaxyId(final Long id);
    /**
     * gets a star by id
     * @param id the Long that is id of star we need to get
     * @return a star for id
     */
    public Star getStarById(final Long id);

    /**
     * gets star by name
     * @param name the String that is name of star we need to get
     * @return a star for name
     */
    public Star getStarByName(final String name);

    /**
     * gets all stars in db
     * @return a set that stores all stars
     */
    public Set<Star> getAllStars();

    /**
     * gets all stars for galaxy id
     * @param id the Long that is a galaxy id
     * @return set of stars for galaxy id
     */
    public Set<Star> getStarsByGalaxyId(final Long id);

    /**
     * gets stars for age diapason
     * @param age the Long that is an age boundary
     * @param flag the Boolean that shows what diapason we need to get
     *             true means to get stars younger than age parameter
     *             so, false means to get stars older than age parameter
     * @return set of stars for an age diapason
     */
    public Set<Star> getStarsByAge(final Long age, final Boolean flag);

    /**
     * gets stars for age diapason
     * @param lowBorder the Long that is a low age boundary
     * @param topBorder the Long than is a top age boundary
     * @return set of stars for an age diapason
     */
    public Set<Star> getStarsByAge(final Long lowBorder, final Long topBorder);

    /**
     * gets stars for mass diapason
     * @param mass the Long that is an age boundary
     * @param flag the Boolean that shows what diapason we need to get
     *             true means to get stars lighter than mass parameter
     *             so, false means to get stars older than mass parameter
     * @return set of stars for a mass diapason
     */
    public Set<Star> getStarsByMass(final Double mass, final Boolean flag);

    /**
     * gets stars for mass diapason
     * @param lowBorder the Double that is a low mass boundary
     * @param topBorder the Double that is a top mass boundary
     * @return set of stars for mass diapason
     */
    public Set<Star> getStarsByMass(final Double lowBorder, final Double topBorder);

    /**
     * gets stars that were discovered on that date
     * @param date the Date that is a discovery date
     * @return the Set that stores stars for date
     */
    public Set<Star> getStarsByDate(final Date date);
    /**
     * gets stars that were discovered earlier or later a date
     * @param date the Date that is boundary date
     * @param flag the Boolean that shows what diapason we need to get
     *             true means to look for earlier discoveries
     *             false means to look for later discoveries
     * @return the Set that stores stars for date diapason
     */
    public Set<Star> getStarsByDate(final Date date, final Boolean flag);
    /**
     * gets stars that were discovered on date diapason
     * @param lowBorder the Date that is low border of diapason
     * @param topBorder the Date that is top border of diapason
     * @return the Set that stores stars for date diapason
     */
    public Set<Star> getStarsByDate(final Date lowBorder, final Date topBorder);
}
