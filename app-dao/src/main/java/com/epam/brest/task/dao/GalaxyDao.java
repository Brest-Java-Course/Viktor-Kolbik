package com.epam.brest.task.dao;

import com.epam.brest.task.domain.Galaxy;
import com.epam.brest.task.domain.Star;

import java.util.Set;

public interface GalaxyDao {

    public Long addGalaxy(Galaxy galaxy);
    public void updateGalaxy(Galaxy galaxy);
    public void removeGalaxy(Long id);

    public Galaxy getGalaxyById(Long id);
    public Galaxy getGalaxyByName(String name);
    public Galaxy getGalaxyByStar(Star star);
    public Set<Galaxy> getAllGalaxies();
    public Set<Galaxy> getGalaxiesClotherThan(Long distance);
    public Set<Galaxy> getGalaxiesFartherThan(Long distance);
}