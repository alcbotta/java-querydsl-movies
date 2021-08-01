package com.github.alcbotta.javaqquerydslmovies.services;

import com.github.alcbotta.javaqquerydslmovies.configuration.DatabaseInit;
import com.github.alcbotta.javaqquerydslmovies.models.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class MovieServiceTest {

    @Autowired
    MovieService movieService;

    @Autowired
    DatabaseInit databaseInit;

    @Before
    public void init() {
        databaseInit.init();
    }

    @Test
    public void testSave_ExpectSucess(){
        String movieName = "that movie with that actor";
        Movie m = Movie.builder().name(movieName).build();
        movieService.save(m);
        Optional<Movie> m2 = movieService.findById(m.getId());
        Assert.assertTrue(m2.isPresent());
        Assert.assertEquals(movieName, m2.get().getName());
    }

}
