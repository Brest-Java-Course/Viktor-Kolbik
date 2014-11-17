package com.epam.brest.task.domain;

import java.sql.Date;

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
     * the Long, that presents an average age of  all stars into this galaxy
     */
    private Long averageAge;

    /**
     * the Long, that presents an average mass of  all stars into this galaxy
     */
    private Double averageMass;

    /**
     * the Date, that presents an date of discovering of the galaxy
     */

    private Date date;

    public Galaxy(){

    }

    public Galaxy(String name, Long distance, Date date) {
        this.name = name;
        this.distance = distance;
        this.date = date;
    }

    public Galaxy(Long galaxyId, String name, Long distance, Date date) {
        this.galaxyId = galaxyId;
        this.name = name;
        this.distance = distance;
        this.date = date;
    }

    public Galaxy(Long galaxyId, String name, Long distance, Date date, Long averageAge, Double averageMass) {
        this.galaxyId = galaxyId;
        this.name = name;
        this.distance = distance;
        this.averageAge = averageAge;
        this.averageMass = averageMass;
        this.date = date;
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

    public Double getAverageMass() {
        return averageMass;
    }

    public void setAverageMass(Double averageMass) {
        this.averageMass = averageMass;
    }

    public Long getAverageAge() {
        return averageAge;
    }

    public void setAverageAge(Long averageAge) {
        this.averageAge = averageAge;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Galaxy{" +
                "galaxyId=" + galaxyId +
                ", name='" + name + '\'' +
                ", distance=" + distance +
                ", averageAge=" + averageAge +
                ", averageMass=" + averageMass +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Galaxy)) return false;

        Galaxy galaxy = (Galaxy) o;

        if (averageAge != null ? !averageAge.equals(galaxy.averageAge) : galaxy.averageAge != null) return false;
        if (averageMass != null ? !averageMass.equals(galaxy.averageMass) : galaxy.averageMass != null) return false;
        if (distance != null ? !distance.equals(galaxy.distance) : galaxy.distance != null) return false;
        if (galaxyId != null ? !galaxyId.equals(galaxy.galaxyId) : galaxy.galaxyId != null) return false;
        if (name != null ? !name.equals(galaxy.name) : galaxy.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = galaxyId != null ? galaxyId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (averageAge != null ? averageAge.hashCode() : 0);
        result = 31 * result + (averageMass != null ? averageMass.hashCode() : 0);
        return result;
    }
}
