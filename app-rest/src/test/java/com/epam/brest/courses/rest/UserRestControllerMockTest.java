package com.epam.brest.courses.rest;

import com.epam.brest.courses.domain.User;
import com.epam.brest.courses.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.annotation.Resource;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-rest-mock-test.xml"})
public class UserRestControllerMockTest {

    private MockMvc mockMvc;

    @Resource
    private UserRestController userRestController;

    @Autowired
    private UserService userService;

    @Before
    public void setUp(){
        this.mockMvc = standaloneSetup(userRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @After
    public void clean() {
        reset(userService);
    }

    @Test
    public void testAddUser() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User(3L, "some", "thing");

        expect(userService.addUser(null)).andReturn(new Long(3L));
        replay(userService);

        String userJson = objectMapper.writeValueAsString(user);
       // System.out.println(userJson);

        this.mockMvc.perform(
                post("/users")
                        .content(userJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated());

        verify();
    }

    @Test
    public void testGetUserById() throws Exception{

        expect(userService.getUserById(5L)).andReturn(UserDataFixture.getUserWithNotNullUserId(5L));
        replay(userService);

        mockMvc.perform(get("/users/5")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"userId\":5,\"login\":\"some login\",\"userName\":\"some name\"}"));
    }

    @Test
    public void testGetUserByLogin() throws Exception{
        User user = UserDataFixture.getNewUser();
        expect(userService.getUserByLogin(user.getLogin())).andReturn(user);
        replay(userService);

        mockMvc.perform(get("/login/{login}")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"userId\":5,\"login\":\"some login\",\"userName\":\"some name\"}"));
    }

    @Test
    public void updateUser() throws Exception {

        User user = new User(3L, "login", "user_name");
        String userJson = new ObjectMapper().writeValueAsString(user);

        userService.updateUser(user);

        replay(userService);

        this.mockMvc.perform(
                put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isOk());

        verify(userService);
    }

    @Test
    public void testGetUsers() throws Exception{
        expect(userService.getUsers()).andReturn(UserDataFixture.getExistingUsers());
        replay();

        this.mockMvc.perform(
                get("/users")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void deleteUserTest() throws Exception {
        User user = UserDataFixture.getNewUser();
        userService.removeUser(3L);
        expectLastCall();

        replay();

        ResultActions result = this.mockMvc.perform(
                delete("/users/3"))
                .andDo(print());
        result.andExpect(status().isOk());
    }
}
