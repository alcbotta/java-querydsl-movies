package com.github.alcbotta.javaqquerydslmovies.controllers;
import com.github.alcbotta.javaqquerydslmovies.configuration.DatabaseInit;
import com.github.alcbotta.javaqquerydslmovies.configuration.ResetDatabaseTestExecutionListener;
import com.github.alcbotta.javaqquerydslmovies.models.Movie;
import com.github.alcbotta.javaqquerydslmovies.repositories.MovieRepository;
import org.junit.Before;
import org.junit.BeforeClass;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.lang.reflect.Type;
import java.util.stream.StreamSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.transaction.annotation.Transactional;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestExecutionListeners(mergeMode =
        TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
        listeners = {ResetDatabaseTestExecutionListener.class}
)
public class MovieControllerTest {
    String baseURL = "api/v1";

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    private final String endpoint = "movies";

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    DatabaseInit databaseInit;

    @Before
    public void init() {
        databaseInit.init();
    }

    @Test
    public void testFindById_1_ExpectSuccess() throws Exception {


        String finalEndpoint = String.format("http://localhost:%s/%s/%s/%d", port, baseURL, endpoint, 1);
        MvcResult result = this.mockMvc.perform(get(finalEndpoint)).andExpect(status().isOk()).andReturn();
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        String resultBody = result.getResponse().getContentAsString();
        Movie m = gson.fromJson (resultBody, Movie.class);
        assertEquals("tropa de elite", m.getName());

    }

    @Test
    public void testFindById_2_ExpectSuccess() throws Exception {

        /**
         select
         movie0_.id as id1_1_0_,
         movie0_.name as name2_1_0_,
         actors1_.movie_id as movie_id1_2_1_,
         actor2_.id as actors_i2_2_1_,
         actor2_.id as id1_0_2_,
         actor2_.name as name2_0_2_
         from
         movies movie0_
         left outer join
         movies_actors actors1_
         on movie0_.id=actors1_.movie_id
         left outer join
         actors actor2_
         on actors1_.actors_id=actor2_.id
         where
         movie0_.id=?
         */


        /**
         select
         movie0_.id as id1_1_0_,
         movie0_.name as name2_1_0_
         from
         movies movie0_
         where
         movie0_.id=?
         */
        String finalEndpoint = String.format("http://localhost:%s/%s/%s/%d", port, baseURL, endpoint, 2);
        MvcResult result = this.mockMvc.perform(get(finalEndpoint)).andReturn();

        assertNotNull(result);
        String resultBody = result.getResponse().getContentAsString();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());


        Movie m = gson.fromJson (resultBody, Movie.class);
        assertEquals("star wars", m.getName());

    }
}
