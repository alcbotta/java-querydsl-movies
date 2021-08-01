package com.github.alcbotta.javaqquerydslmovies.configuration;

import com.github.alcbotta.javaqquerydslmovies.models.Movie;
import com.github.alcbotta.javaqquerydslmovies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
public class DatabaseInit {
    @Autowired
    private MovieRepository movieRepository;

    public void init() {
        Movie m = Movie.builder().name("tropa de elite").build();
        movieRepository.save(m);
    }
}