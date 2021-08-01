package com.github.alcbotta.javaqquerydslmovies.configuration;

import com.github.alcbotta.javaqquerydslmovies.models.Actor;
import com.github.alcbotta.javaqquerydslmovies.models.Movie;
import com.github.alcbotta.javaqquerydslmovies.repositories.ActorRepository;
import com.github.alcbotta.javaqquerydslmovies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class DatabaseInit {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    public void init() {


        Movie m = Movie.builder().name("tropa de elite").build();
        movieRepository.save(m);

        Actor actor1 = Actor.builder().name("natalie").build();
        Actor actor2 = Actor.builder().name("liam").build();

        Set<Actor> actorSet1 = Stream.of(actor1, actor2).collect(Collectors.toCollection(HashSet::new));
        m = Movie.builder().name("star wars").actors(actorSet1).build();


        movieRepository.save(m);
    }
}