package com.epam.brest.task.dao;

import com.epam.brest.task.domain.Galaxy;

import java.sql.Date;
import java.util.Set;

public interface GalaxyDao {

    public Long addGalaxy(final Galaxy galaxy);
    public void updateGalaxy(final Galaxy galaxy);
    public void removeGalaxy(final Long id);

    public Galaxy getGalaxyById(final Long id);
    public Galaxy getGalaxyByName(final String name);
    public Set<Galaxy> getAllGalaxies();

    public Set<Galaxy> getGalaxiesByDate(final Date date);
    public Set<Galaxy> getGalaxiesByDate(final Date date, final Boolean flag);
    public Set<Galaxy> getGalaxiesByDate(final Date lowBorder, final Date topBorder);

    public Set<Galaxy> getGalaxiesByDistance(final Long distance, final Boolean flag);
    public Set<Galaxy> getGalaxiesByDistanceInterval(final Long lowBorder, final Long topBorder);
}