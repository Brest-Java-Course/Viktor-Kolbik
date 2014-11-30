package com.epam.brest.task.rest_server;

import com.epam.brest.task.domain.Galaxy;
import com.epam.brest.task.domain.Star;
import com.epam.brest.task.service.GalaxyService;
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
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-rest-server-mock-test.xml"})
public class GalaxyRestControllerMockTest {

    private static final String NAME = "name";
    private static final Long ID = 8L;
    private static final Long DISTANCE = 3000L;
    private static final Date DATE = new Date(2014 - 1900, 5, 5);

    private MockMvc mockMvc;

    @Resource
    private GalaxyRestController galaxyRestController;

    @Autowired
    private GalaxyService galaxyService;

    private static final String GALAXY_REST_ROOT = "/restGalaxy";
    private static final String SET_WE_GET = "[{\"galaxyId\":9,\"name\":\"name1\",\"distance\":3001," +
            "\"averageAge\":null,\"averageMass\":null,\"date\":\"2014-06-05\"}," +
            "{\"galaxyId\":8,\"name\":\"name\",\"distance\":3000," +
            "\"averageAge\":null,\"averageMass\":null,\"date\":\"2014-06-05\"}]";


    @Before
    public void setUp() {
        this.mockMvc = standaloneSetup(galaxyRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @After
    public void clean() {
        reset(galaxyService);
    }

    @Test
    public void testAddStar() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Galaxy galaxy = new Galaxy(ID, NAME, DISTANCE, DATE);
        expect(galaxyService.addGalaxy(galaxy)).andReturn(ID);
        replay(galaxyService);

        String galaxyJson = objectMapper.writeValueAsString(galaxy);

        this.mockMvc.perform(
                post(GALAXY_REST_ROOT)
                        .content(galaxyJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated());

        verify(galaxyService);
    }

    @Test
    public void testUpdateStar() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Galaxy galaxy = new Galaxy(ID, NAME, DISTANCE, DATE);

        galaxyService.updateGalaxy(galaxy);
        expectLastCall().once();

        replay(galaxyService);

        String starJson = objectMapper.writeValueAsString(galaxy);

        this.mockMvc.perform(
                put(GALAXY_REST_ROOT)
                        .content(starJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isAccepted());

        verify(galaxyService);
    }

    @Test
    public void testRemoveGalaxy() throws Exception{
        galaxyService.removeGalaxy(ID);
        expectLastCall().once();

        replay(galaxyService);

        this.mockMvc.perform(
                delete(GALAXY_REST_ROOT + "/" + ID))
                .andDo(print())
                .andExpect(status().isAccepted());

        verify(galaxyService);
    }

    @Test
    public void testGetGalaxyById() throws Exception {
        Galaxy galaxy = new Galaxy(ID, NAME, DISTANCE, DATE);

        expect(galaxyService.getGalaxyById(ID))
                .andReturn(galaxy);
        replay(galaxyService);

        mockMvc.perform(get(GALAXY_REST_ROOT + "/galaxyById/" + ID)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"galaxyId\":8,\"name\":\"name\",\"distance\":3000," +
                        "\"averageAge\":null,\"averageMass\":null,\"date\":\"2014-06-05\"}"));

        verify(galaxyService);
    }

    @Test
    public void testGetGalaxyByName() throws Exception {
        Galaxy galaxy = new Galaxy(ID, NAME, DISTANCE, DATE);

        expect(galaxyService.getGalaxyByName(NAME)).andReturn(galaxy);
        replay(galaxyService);

        mockMvc.perform(get(GALAXY_REST_ROOT + "/galaxyByName/" + NAME)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"galaxyId\":8,\"name\":\"name\",\"distance\":3000," +
                        "\"averageAge\":null,\"averageMass\":null,\"date\":\"2014-06-05\"}"));

        verify(galaxyService);
    }

    @Test
    public void testGetAllGalaxies() throws Exception{
        Set<Galaxy> galaxies = new HashSet<Galaxy>(2);
        galaxies.add(new Galaxy(ID, NAME, DISTANCE, DATE));
        galaxies.add(new Galaxy(ID + 1, NAME + 1, DISTANCE + 1, DATE));


        expect(galaxyService.getAllGalaxies()).andReturn(galaxies);
        replay(galaxyService);

        mockMvc.perform(get(GALAXY_REST_ROOT)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(SET_WE_GET));

        verify(galaxyService);
    }

    @Test
    public void testGetGalaxiesByDistance0() throws Exception {
        Set<Galaxy> galaxies = new HashSet<Galaxy>(2);
        galaxies.add(new Galaxy(ID, NAME, DISTANCE, DATE));
        galaxies.add(new Galaxy(ID + 1, NAME + 1, DISTANCE + 1, DATE));

        expect(galaxyService.getGalaxiesByDistance(DISTANCE, true)).andReturn(galaxies);
        replay(galaxyService);

        mockMvc.perform(get(GALAXY_REST_ROOT + "/galaxiesByDistanceAndFlag/" + DISTANCE + "_" + true)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(SET_WE_GET));

        verify(galaxyService);
    }

    @Test
    public void testGetGalaxiesByDistance1() throws Exception {
        Set<Galaxy> galaxies = new HashSet<Galaxy>(2);
        galaxies.add(new Galaxy(ID, NAME, DISTANCE, DATE));
        galaxies.add(new Galaxy(ID + 1, NAME + 1, DISTANCE + 1, DATE));

        expect(galaxyService.getGalaxiesByDistance(DISTANCE, DISTANCE + 1)).andReturn(galaxies);
        replay(galaxyService);

        mockMvc.perform(get(GALAXY_REST_ROOT + "/galaxiesByTwoDistance/" + DISTANCE + "_" + (DISTANCE + 1))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(SET_WE_GET));

        verify(galaxyService);
    }

    @Test
    public void testGetGalaxyByDate0() throws Exception {
        Set<Galaxy> galaxies = new HashSet<Galaxy>(2);
        galaxies.add(new Galaxy(ID, NAME, DISTANCE, DATE));
        galaxies.add(new Galaxy(ID + 1, NAME + 1, DISTANCE + 1, DATE));


        expect(galaxyService.getGalaxiesByDate(DATE)).andReturn(galaxies);
        replay(galaxyService);

        mockMvc.perform(get(GALAXY_REST_ROOT + "/galaxiesByDate/" + DATE)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(SET_WE_GET));

        verify(galaxyService);
    }

    @Test
    public void testGetGalaxyByDate1() throws Exception {
        Set<Galaxy> galaxies = new HashSet<Galaxy>(2);
        galaxies.add(new Galaxy(ID, NAME, DISTANCE, DATE));
        galaxies.add(new Galaxy(ID + 1, NAME + 1, DISTANCE + 1, DATE));

        expect(galaxyService.getGalaxiesByDate(DATE, true)).andReturn(galaxies);
        replay(galaxyService);

        mockMvc.perform(get(GALAXY_REST_ROOT + "/galaxiesByDateAndFlag/" + DATE + "_" + true)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(SET_WE_GET));

        verify(galaxyService);
    }

    @Test
    public void testGetGalaxyByDate2() throws Exception {
        Set<Galaxy> galaxies = new HashSet<Galaxy>(2);
        galaxies.add(new Galaxy(ID, NAME, DISTANCE, DATE));
        galaxies.add(new Galaxy(ID + 1, NAME + 1, DISTANCE + 1, DATE));

        Date topBorder = new Date(2014 - 1900, 6, 5);

        expect(galaxyService.getGalaxiesByDate(DATE, topBorder)).andReturn(galaxies);
        replay(galaxyService);

        mockMvc.perform(get(GALAXY_REST_ROOT + "/galaxiesByTwoDate/" + DATE + "_" + topBorder)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(SET_WE_GET));

        verify(galaxyService);
    }
}