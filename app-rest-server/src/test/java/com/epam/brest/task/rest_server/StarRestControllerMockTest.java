package com.epam.brest.task.rest_server;

import com.epam.brest.task.domain.Star;
import com.epam.brest.task.service.StarService;
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
public class StarRestControllerMockTest {

    private static final String NAME = "name";
    private static final Long ID = 8L;
    private static final Long AGE = 3000L;
    private static final Long MASS = 51L;
    private static final Date DATE = new Date(2014 - 1900, 5, 5);
    private static final String SET_WE_GET = "[{\"starId\":8,\"name\":\"name\",\"age\":3000,\"mass\":51,\"date\":\"2014-06-05\",\"galaxyId\":8}," +
            "{\"starId\":9,\"name\":\"name1\",\"age\":3001,\"mass\":52,\"date\":\"2014-06-05\",\"galaxyId\":9}]";


    private MockMvc mockMvc;

    @Resource
    private StarRestController starRestController;

    private static final String STAR_REST_ROOT = "/restStar";

    @Autowired
    private StarService starService;

    @Before
    public void setUp() {
        this.mockMvc = standaloneSetup(starRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @After
    public void clean() {
        reset(starService);
    }

    @Test
    public void testAddStar() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Star star = new Star(ID, NAME, AGE, MASS, DATE, ID);
        expect(starService.addStar(star)).andReturn(ID);
        replay(starService);

        String starJson = objectMapper.writeValueAsString(star);

        this.mockMvc.perform(
                post(STAR_REST_ROOT)
                        .content(starJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated());

        verify(starService);
    }

    @Test
    public void testUpdateStar() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Star star = new Star(ID, NAME, AGE, MASS, DATE, ID);

        starService.updateStar(star);
        expectLastCall().once();

        replay(starService);

        String starJson = objectMapper.writeValueAsString(star);

        this.mockMvc.perform(
                put(STAR_REST_ROOT)
                        .content(starJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isAccepted());

        verify(starService);
    }

    @Test
    public void testRemoveStar() throws Exception{
        starService.removeStar(ID);
        expectLastCall().once();

        replay(starService);

        this.mockMvc.perform(
                delete(STAR_REST_ROOT + "/" + ID))
                .andDo(print())
                .andExpect(status().isAccepted());

        verify(starService);
    }

    @Test
    public void testGetStarById() throws Exception {

        expect(starService.getStarById(ID))
                .andReturn(new Star(ID, NAME, AGE, MASS, DATE, ID));
        replay(starService);

        mockMvc.perform(get(STAR_REST_ROOT + "/starById/" + ID)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"starId\":8,\"name\":\"name\",\"age\":3000,\"mass\":51,\"date\":\"2014-06-05\",\"galaxyId\":8}"));

        verify(starService);
    }

    @Test
    public void testGetStarsByGalaxyId() throws Exception {

        Set<Star> stars = new HashSet<Star>();
        stars.add(new Star(ID, NAME, AGE, MASS, DATE, ID));
        stars.add(new Star(ID + 1, NAME + 1, AGE + 1, MASS + 1, DATE, ID + 1));

        expect(starService.getStarsByGalaxyId(ID)).andReturn(stars);
        replay(starService);

        mockMvc.perform(get(STAR_REST_ROOT + "/starsByGalaxyId/" + ID)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(SET_WE_GET));

        verify(starService);
    }

    @Test
    public void testGetStarByName() throws Exception {

        expect(starService.getStarByName(NAME)).andReturn(new Star(ID, NAME, AGE, MASS, DATE, ID));
        replay(starService);

        mockMvc.perform(get(STAR_REST_ROOT + "/starByName/" + NAME)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"starId\":8,\"name\":\"name\",\"age\":3000,\"mass\":51,\"date\":\"2014-06-05\",\"galaxyId\":8}"));

        verify(starService);
    }

    @Test
    public void testGetAllStars() throws Exception{
        Set<Star> stars = new HashSet<Star>();
        stars.add(new Star(ID, NAME, AGE, MASS, DATE, ID));
        stars.add(new Star(ID + 1, NAME + 1, AGE + 1, MASS + 1, DATE, ID + 1));


        expect(starService.getAllStars()).andReturn(stars);
        replay(starService);

        mockMvc.perform(get(STAR_REST_ROOT)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(SET_WE_GET));

        verify(starService);
    }

    @Test
    public void testGetStarByAge0() throws Exception {
        Set<Star> stars = new HashSet<Star>();
        stars.add(new Star(ID, NAME, AGE, MASS, DATE, ID));
        stars.add(new Star(ID + 1, NAME + 1, AGE + 1, MASS + 1, DATE, ID + 1));


        expect(starService.getStarsByAge(AGE, true)).andReturn(stars);
        replay(starService);

        mockMvc.perform(get(STAR_REST_ROOT + "/starsByAgeAndFlag/" + AGE + "_" + true)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(SET_WE_GET));

        verify(starService);
    }

    @Test
    public void testGetStarByAge1() throws Exception {
        Set<Star> stars = new HashSet<Star>();
        stars.add(new Star(ID, NAME, AGE, MASS, DATE, ID));
        stars.add(new Star(ID + 1, NAME + 1, AGE + 1, MASS + 1, DATE, ID + 1));


        expect(starService.getStarsByAge(AGE, AGE + 1)).andReturn(stars);
        replay(starService);

        mockMvc.perform(get(STAR_REST_ROOT + "/starsByTwoAge/" + AGE + "_" + (AGE + 1))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(SET_WE_GET));

        verify(starService);
    }

    @Test
    public void testGetStarByMass0() throws Exception {
        Set<Star> stars = new HashSet<Star>();
        stars.add(new Star(ID, NAME, AGE, MASS, DATE, ID));
        stars.add(new Star(ID + 1, NAME + 1, AGE + 1, MASS + 1, DATE, ID + 1));


        expect(starService.getStarsByMass(MASS, true)).andReturn(stars);
        replay(starService);

        mockMvc.perform(get(STAR_REST_ROOT + "/starsByMassAndFlag/" + MASS + "_" + true)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(SET_WE_GET));

        verify(starService);
    }

    @Test
    public void testGetStarByMass1() throws Exception {
        Set<Star> stars = new HashSet<Star>();
        stars.add(new Star(ID, NAME, AGE, MASS, DATE, ID));
        stars.add(new Star(ID + 1, NAME + 1, AGE + 1, MASS + 1, DATE, ID + 1));

        expect(starService.getStarsByMass(MASS, MASS + 1)).andReturn(stars);
        replay(starService);

        mockMvc.perform(get(STAR_REST_ROOT + "/starsByTwoMass/" + MASS + "_" + (MASS + 1))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(SET_WE_GET));

        verify(starService);
    }

    @Test
    public void testGetStarByDate0() throws Exception {
        Set<Star> stars = new HashSet<Star>();
        stars.add(new Star(ID, NAME, AGE, MASS, DATE, ID));
        stars.add(new Star(ID + 1, NAME + 1, AGE + 1, MASS + 1, DATE, ID + 1));


        expect(starService.getStarsByDate(DATE)).andReturn(stars);
        replay(starService);

        mockMvc.perform(get(STAR_REST_ROOT + "/starsByDate/" + DATE)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(SET_WE_GET));

        verify(starService);
    }

    @Test
    public void testGetStarByDate1() throws Exception {
        Set<Star> stars = new HashSet<Star>();
        stars.add(new Star(ID, NAME, AGE, MASS, DATE, ID));
        stars.add(new Star(ID + 1, NAME + 1, AGE + 1, MASS + 1, DATE, ID + 1));

        expect(starService.getStarsByDate(DATE, true)).andReturn(stars);
        replay(starService);

        mockMvc.perform(get(STAR_REST_ROOT + "/starsByDateAndFlag/" + DATE + "_" + true)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(SET_WE_GET));

        verify(starService);
    }

    @Test
    public void testGetStarByDate2() throws Exception {
        Set<Star> stars = new HashSet<Star>();
        stars.add(new Star(ID, NAME, AGE, MASS, DATE, ID));
        stars.add(new Star(ID + 1, NAME + 1, AGE + 1, MASS + 1, DATE, ID + 1));
        Date topBorder = new Date(2014 - 1900, 6, 5);

        expect(starService.getStarsByDate(DATE, topBorder)).andReturn(stars);
        replay(starService);

        mockMvc.perform(get(STAR_REST_ROOT + "/starsByTwoDate/" + DATE + "_" + topBorder)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(SET_WE_GET));

        verify(starService);
    }

}