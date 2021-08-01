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
    public void testList_emptyParameters_ExpectSuccess() throws Exception {


        String finalEndpoint = String.format("http://localhost:%s/%s/%s/%d", port, baseURL, endpoint, 1);
        MvcResult result = this.mockMvc.perform(get(finalEndpoint)).andExpect(status().isOk()).andReturn();
        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        String resultBody = result.getResponse().getContentAsString();
        Movie m = gson.fromJson (resultBody, Movie.class);
        assertEquals("tropa de elite", m.getName());

    }
}
