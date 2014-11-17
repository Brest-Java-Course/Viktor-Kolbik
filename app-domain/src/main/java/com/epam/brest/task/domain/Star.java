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
     * the Double, that presents a mass of a star
     */
    private Double mass;

    /**
     * the Date, that presents a date of a discovering of a star
     */
    private Date date;

    public Star() {
    }

    public Star(String name, Long age, Double mass, Date date) {
        this.name = name;
        this.age = age;
        this.mass = mass;
        this.date = date;
    }

    public Star(Long starId, String name, Long age, Double mass, Date date) {
        this.starId = starId;
        this.name = name;
        this.age = age;
        this.mass = mass;
        this.date = date;
    }

    public Long getStarId() {
        return starId;
    }

    public void setStarId(Long starId) {
        this.starId = starId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Star{" +
                "starId=" + starId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", mass=" + mass +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Star)) return false;

        Star star = (Star) o;

        if (age != null ? !age.equals(star.age) : star.age != null) return false;
        if (date != null ? !date.equals(star.date) : star.date != null) return false;
        if (mass != null ? !mass.equals(star.mass) : star.mass != null) return false;
        if (name != null ? !name.equals(star.name) : star.name != null) return false;
        return !(starId != null ? !starId.equals(star.starId) : star.starId != null);

    }

    @Override
    public int hashCode() {
        int result = starId != null ? starId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (mass != null ? mass.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}