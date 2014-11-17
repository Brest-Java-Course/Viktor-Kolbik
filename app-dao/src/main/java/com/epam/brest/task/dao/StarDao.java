package com.epam.brest.task.dao;

import com.epam.brest.task.domain.Star;

import java.util.Set;

public interface StarDao {

    public Long addStar();
    public void updateStar(Star star);
    public void removeStar(Long id);

    public Star getStarById(Long id);
    public Star getStarByName(String name);
    public Set<Star> getAllStars();
    public Set<Star> getStarsByGalaxyId(Long Id, Long Pos, Long amount);
    public Set<Star> getStarsByAge(Long age, Boolean flag);
    public Set<Star> getStarsByAge(Long lowBorder, Long topBorder);
    public Set<Star> getStarsByMass(Double mass, Boolean flag);
    public Set<Star> getStarsByMass(Double lowBorder, Double topBorder);
}
