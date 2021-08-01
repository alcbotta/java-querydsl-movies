package com.github.alcbotta.javaqquerydslmovies.services;

import com.github.alcbotta.javaqquerydslmovies.configuration.DatabaseInit;
import com.github.alcbotta.javaqquerydslmovies.dto.movie.find.MovieDTO;
import com.github.alcbotta.javaqquerydslmovies.models.Movie;
import com.google.gson.Gson;
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


    @Test
    public void testFindByID_ExpectFailure(){
        Optional<Movie> m = movieService.findById(2L);
        Assert.assertTrue(m.isPresent());
        Gson gson = new Gson();

        Assert.assertThrows(Exception.class, () -> {
            // failed to lazily initialize a collection of role: com.github.alcbotta.javaqquerydslmovies.models.Movie.actors, could not initialize prox
            String s = gson.toJson(m);
        });

    }

    @Test
    public void testFindByID_DTO_ExpectSucess(){
        Optional<MovieDTO> m = movieService.findByIdDto(2L);
        Assert.assertTrue(m.isPresent());
        Gson gson = new Gson();

        /**
         Hibernate:
         select
         movie0_.id as id1_1_0_,
         movie0_.name as name2_1_0_
         from
         movies movie0_
         where
         movie0_.id=?

         Hibernate:
         select
         actors0_.movie_id as movie_id1_2_0_,
         actors0_.actors_id as actors_i2_2_0_,
         actor1_.id as id1_0_1_,
         actor1_.name as name2_0_1_
         from
         movies_actors actors0_
         inner join
         actors actor1_
         on actors0_.actors_id=actor1_.id
         where
         actors0_.movie_id=?
         */


        String s = gson.toJson(m);

    }

    @Test
    public void testFindByID_DTO_withCrew_ExpectSucess(){
        Optional<MovieDTO> m = movieService.findByIdDto(3L);
        Assert.assertTrue(m.isPresent());
        Gson gson = new Gson();
        String s = gson.toJson(m);

    }

}
