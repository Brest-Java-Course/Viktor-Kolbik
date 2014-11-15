package com.epam.brest.courses.client;

import com.epam.brest.courses.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by simpson on 14.11.14.
 */
public class RestClientTest {
    static private final String HOST = "http://host";
    private RestClient client;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp(){
        client = new RestClient(HOST);
        mockServer = MockRestServiceServer.createServer(client.getRestTemplate());
    }

    @After
    public void check(){
        mockServer.verify();
    }

    @Test
    public void version(){
        mockServer.expect(requestTo(HOST + "/version"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("123", MediaType.APPLICATION_JSON));

        String version = client.getRestVesrsion();
        assertEquals("123", version);
    }

    @Test
    public void addUserTest(){
        mockServer.expect(requestTo(HOST + "/users"))
                .andExpect(method(HttpMethod.POST))
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(content().string("{\"userId\":null,\"login\":\"login5\",\"userName\":\"namr3\"}"))
        .andRespond(withSuccess("4", MediaType.APPLICATION_JSON));

        User user = new User("login5", "namr3");
        Long id = client.addUser(user);
        assertEquals(4L, (long)id);
    }

    @Test
    public void getUserById(){
        mockServer.expect(requestTo(HOST + "/users/1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{\"userId\":\"1\",\"login\":\"login5\",\"userName\":\"namr3\"}", MediaType.APPLICATION_JSON));

        User user = client.getUserById(1L);
        assertNotNull(user);
        assertNotNull(user.getUserId());
    }

    @Test
    public void removeUser(){
        mockServer.expect(requestTo(HOST + "/users/1"))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withSuccess());

        client.removeUser(1L);

    }
}
