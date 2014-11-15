package com.epam.brest.task.domain;

import java.util.Set;

/**
 * POJO object that presents a Galaxy.
 * @author  Viktor Kolbik
 * @version 1.0
 */
public class Galaxy {
    /**
     * the Long, that presents the identifier of a galaxy
     */
    private Long galaxyId;

    /**
     * the String, that presents a galaxy name
     */
    private String name;

    /**
     * the Long, that presents a distance from Earth to the galaxy
     */
    private Long distance;

    /**
     * the Set, that stores all stars at this galaxy
     */
    private Set<Star> stars;

    public Galaxy(){

    }

    public Galaxy(String name, Long distance) {
        this.name = name;
        this.distance = distance;
    }

    public Galaxy(Long galaxyId, String name, Long distance) {
        this.galaxyId = galaxyId;
        this.name = name;
        this.distance = distance;
    }

    public Galaxy(String name, Long distance, Set<Star> stars) {
        this.name = name;
        this.distance = distance;
        this.stars = stars;
    }

    public Galaxy(Long galaxyId, String name, Long distance, Set<Star> stars) {
        this.galaxyId = galaxyId;
        this.name = name;
        this.distance = distance;
        this.stars = stars;
    }

    public Set<Star> getStars() {
        return stars;
    }

    public void setStars(Set<Star> stars) {
        this.stars = stars;
    }

    public Long getGalaxyId() {
        return galaxyId;
    }

    public void setGalaxyId(Long galaxyId) {
        this.galaxyId = galaxyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Galaxy{" +
                "galaxyId=" + galaxyId +
                ", name='" + name + '\'' +
                ", distance=" + distance +
                ", stars=" + stars +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Galaxy)) return false;

        Galaxy galaxy = (Galaxy) o;

        if (distance != null ? !distance.equals(galaxy.distance) : galaxy.distance != null) return false;
        if (galaxyId != null ? !galaxyId.equals(galaxy.galaxyId) : galaxy.galaxyId != null) return false;
        if (name != null ? !name.equals(galaxy.name) : galaxy.name != null) return false;
        if (stars != null ? !stars.equals(galaxy.stars) : galaxy.stars != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = galaxyId != null ? galaxyId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (stars != null ? stars.hashCode() : 0);
        return result;
    }
}
