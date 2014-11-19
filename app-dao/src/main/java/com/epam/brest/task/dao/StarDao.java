package com.epam.brest.task.dao;

import com.epam.brest.task.domain.Star;

import java.sql.Date;
import java.util.Set;

public interface StarDao {

    public Long addStar(final Star star);
    public void updateStar(final Star star);
    public void removeStar(final Long id);
    public void removeStarsByGalaxyId(final Long id);

    public Star getStarById(final Long id);
    public Star getStarByName(final String name);
    public Set<Star> getAllStars();

    public Set<Star> getStarsByGalaxyId(final Long id);
    public Set<Star> getStarsByAge(final Long age, final Boolean flag);
    public Set<Star> getStarsByAge(final Long lowBorder, final Long topBorder);
    public Set<Star> getStarsByMass(final Double mass, final Boolean flag);
    public Set<Star> getStarsByMass(final Double lowBorder, final Double topBorder);

    public Set<Star> getStarsByDate(final Date date);
    public Set<Star> getStarsByDate(final Date date, final Boolean flag);
    public Set<Star> getStarsByDate(final Date lowBorder, final Date topBorder);
}