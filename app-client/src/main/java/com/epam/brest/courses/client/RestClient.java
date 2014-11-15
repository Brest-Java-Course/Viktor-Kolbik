package com.epam.brest.courses.client;


import com.epam.brest.courses.domain.User;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by mentee-42 on 12.11.14.
 */
public class RestClient {


    private String host;

    private RestTemplate restTemplate = new RestTemplate();

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RestClient(String host) {
        this.host = host;
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);

    }

    public String getRestVesrsion() {

        return restTemplate.getForObject(host + "/version", String.class);
    }

    public User getUserById(Long id) {
        return restTemplate.getForObject(host + "/users/" + id, User.class);
    }

    public Long addUser(User user){
        return restTemplate.postForObject(host + "/users", user, Long.class);
    }

    public void removeUser(Long id){
        restTemplate.delete(host + "/users/" + id);
    }
}