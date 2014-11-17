package com.epam.brest.task.dao;

import com.epam.brest.task.domain.Galaxy;

import java.sql.Date;
import java.util.Set;

public interface GalaxyDao {

    public Long addGalaxy(Galaxy galaxy);
    public void updateGalaxy(Galaxy galaxy);
    public void removeGalaxy(Long id);

    public Galaxy getGalaxyById(Long id);
    public Galaxy getGalaxyByName(String name);
    public Galaxy getGalaxyByStarId(Long id);
    public Set<Galaxy> getAllGalaxies();

    public Set<Galaxy> getGalaxiesByDate(Date date);
    public Set<Galaxy> getGalaxiesByDate(Date date, Boolean flag);
    public Set<Galaxy> getGalaxiesByDate(Date lowBorder, Date topBorder);

    /**
     *
     * @param distance
     * @param flag false - clother then distance, true - farther then distance
     * @return
     */
    public Set<Galaxy> getGalaxiesByDistance(Long distance, Boolean flag);
    public Set<Galaxy> getGalaxiesByDistance(Long lowBorder, Long topBorder);
    public Long getAverageMassOfStars();
    public Long getAverageAgeOfStars();
}