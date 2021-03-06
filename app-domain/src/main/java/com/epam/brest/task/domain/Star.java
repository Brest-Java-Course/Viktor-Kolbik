package com.epam.brest.task.domain;

import java.sql.Date;

/**
 * POJO object that presents a Star.
 * @author  Viktor Kolbik
 * @version 1.0
 */
public class Star {
    /**
     * the Long, that presents an identifier of a star
     */
    private Long starId;

    /**
     * the String, that presents a name of a star
     */
    private String name;

    /**
     * the Long, that presents age of a star
     */
    private Long age;

    /**
     * the Long, that presents a mass of a star
     */
    private Long mass;

    /**
     * the Date, that presents a date of a discovering of a star
     */
    private Date date;

    private Long galaxyId;

    public Star() {
    }

    public Star(String name, Long age, Long mass, Date date) {
        this.name = name;
        this.age = age;
        this.mass = mass;
        this.date = date;
    }

    public Star(Long starId, String name, Long age, Long mass, Date date, Long galaxyId) {
        this.starId = starId;
        this.name = name;
        this.age = age;
        this.mass = mass;
        this.date = date;
        this.galaxyId = galaxyId;
    }

    public Long getStarId() {
        return starId;
    }

    public void setStarId(final Long starId) {
        this.starId = starId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(final Long age) {
        this.age = age;
    }

    public Long getMass() {
        return mass;
    }

    public void setMass(final Long mass) {
        this.mass = mass;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public Long getGalaxyId() {
        return galaxyId;
    }

    public void setGalaxyId(Long galaxyId) {
        this.galaxyId = galaxyId;
    }

    @Override
    public String toString() {
        return "Star{" +
                "starId=" + starId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", mass=" + mass +
                ", date=" + date +
                ", galaxyId=" + galaxyId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Star)) return false;


        Star star = (Star) o;
        if(star.getDate() != null && this.date != null) {
            star.setDate(new Date(star.getDate().getYear(), star.getDate().getMonth(), star.getDate().getDate()));
        }

        if (age != null ? !age.equals(star.age) : star.age != null) return false;
        if (date != null ? !date.equals(star.date) : star.date != null) return false;
        if (galaxyId != null ? !galaxyId.equals(star.galaxyId) : star.galaxyId != null) return false;
        if (mass != null ? !mass.equals(star.mass) : star.mass != null) return false;
        if (name != null ? !name.equals(star.name) : star.name != null) return false;
        if (starId != null ? !starId.equals(star.starId) : star.starId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = starId != null ? starId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (mass != null ? mass.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (galaxyId != null ? galaxyId.hashCode() : 0);
        return result;
    }
}