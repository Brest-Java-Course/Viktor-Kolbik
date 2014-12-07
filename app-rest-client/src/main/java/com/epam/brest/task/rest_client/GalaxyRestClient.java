package com.epam.brest.task.rest_client;

import com.epam.brest.task.domain.Galaxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Repository
public class GalaxyRestClient {

    private static final Logger LOGGER = LogManager.getLogger(GalaxyRestClient.class);

    private static final String SERVER_URL = "http://localhost:8080/universeServer/restGalaxy/";

    private RestTemplate restTemplate = new RestTemplate();

    public Long addGalaxy(Galaxy galaxy){
        LOGGER.debug("starts with " + galaxy);
        Long id = null;

        Assert.notNull(galaxy);
        Assert.isNull(galaxy.getGalaxyId());
        Assert.isTrue(galaxy.getName() != null && !galaxy.getName().trim().equals(""));
        Assert.notNull(galaxy.getDistance());
        Assert.isTrue(galaxy.getDistance() > 0);
        Assert.notNull(galaxy.getDate());
        Assert.isNull(galaxy.getAverageAge());
        Assert.isNull(galaxy.getAverageMass());

        id = restTemplate.postForObject(SERVER_URL, galaxy, Long.class);

        LOGGER.debug("ends with id = " + id);
        return id;
    }

    public void updateGalaxy(Galaxy galaxy){
        LOGGER.debug("starts with " + galaxy);

        Assert.notNull(galaxy);
        Assert.notNull(galaxy.getGalaxyId());
        Assert.isTrue(galaxy.getGalaxyId() >= 0);
        Assert.isTrue(galaxy.getName() != null && !galaxy.getName().trim().equals(""));
        Assert.notNull(galaxy.getDistance());
        Assert.isTrue(galaxy.getDistance() > 0);
        Assert.notNull(galaxy.getDate());
        Assert.isNull(galaxy.getAverageAge());
        Assert.isNull(galaxy.getAverageMass());

        restTemplate.put(SERVER_URL, galaxy);

        LOGGER.debug("ends");
    }

    public void removeGalaxy(Long id){
        LOGGER.debug("starts with " + id);

        Assert.notNull(id);
        Assert.isTrue(id >= 0);

        restTemplate.delete(SERVER_URL + id, id);

        LOGGER.debug("ends");
    }

    public Galaxy getGalaxyById(Long id){
        LOGGER.debug("starts with " + id);

        Galaxy galaxy = restTemplate.getForObject(SERVER_URL + "/galaxyById/" + id, Galaxy.class, id);

        LOGGER.debug("ends with " + galaxy);

        return galaxy;
    }

    public Set<Galaxy> getAllGalaxies(){
        LOGGER.debug("starts ");

        Set<Galaxy> galaxy = restTemplate.getForObject(SERVER_URL, Set.class);

        LOGGER.debug("ends with " + galaxy);

        return galaxy;
    }
}
