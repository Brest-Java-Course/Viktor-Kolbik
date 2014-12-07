package com.epam.brest.task.rest_client;

import com.epam.brest.task.domain.Star;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Repository
public class StarRestClient {

    private static final Logger LOGGER = LogManager.getLogger(StarRestClient.class);

    private static final String SERVER_URL = "http://localhost:8080/universeServer/restStar/";

    private RestTemplate restTemplate = new RestTemplate();


    public Long addStar(Star star) {
        LOGGER.debug("starts with " + star);

        Long id = null;

        Assert.notNull(star);
        Assert.isNull(star.getStarId());
        Assert.isTrue(star.getName() != null && !star.getName().trim().equals(""));
        Assert.notNull(star.getMass());
        Assert.isTrue(!(star.getMass() <= 0));
        Assert.isTrue(!(star.getAge() <= 100L));
        Assert.notNull(star.getDate());

        id = restTemplate.postForObject(SERVER_URL, star, Long.class);

        LOGGER.debug("ends with id = " + id);
        return id;
    }

    public void updateStar(Star star) {
        LOGGER.debug("starts with " + star);

        Assert.notNull(star);
        Assert.notNull(star.getStarId());
        Assert.isTrue(star.getName() != null && !star.getName().trim().equals(""));
        Assert.notNull(star.getMass());
        Assert.isTrue(!(star.getMass() <= 0));
        Assert.isTrue(!(star.getAge() <= 100L));
        Assert.notNull(star.getDate());

        restTemplate.put(SERVER_URL, star);

        LOGGER.debug("ends");

    }

    public void removeStar(Long id) {
        LOGGER.debug("starts with " + id);

        Assert.notNull(id);
        Assert.isTrue(id >= 0);

        restTemplate.delete(SERVER_URL + id, id);

        LOGGER.debug("ends");
    }

    public Star getStarById(Long id) {
        LOGGER.debug("starts with " + id);

        Assert.notNull(id);
        Assert.isTrue(id >= 0);

        Star star = restTemplate.getForObject(SERVER_URL + "/starById/" + id, Star.class, id);

        LOGGER.debug("ends with " + star);

        return star;
    }

    public Set<Star> getStarsByGalaxyId(Long id) {
        LOGGER.debug("starts with " + id);

        Assert.notNull(id);
        Assert.isTrue(id >= 0);

        Set<Star> stars = restTemplate.getForObject(SERVER_URL + "/starsByGalaxyId/" + id, Set.class, id);

        LOGGER.debug("ends with " + stars);

        return stars;
    }

    public Set getAllStars() {
        LOGGER.debug("starts ");

        Set<Star> stars = restTemplate.getForObject(SERVER_URL, Set.class);

        LOGGER.debug("ends with " + stars);

        return stars;
    }
}