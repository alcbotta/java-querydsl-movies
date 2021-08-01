package com.github.alcbotta.javaqquerydslmovies.configuration;

import com.github.alcbotta.javaqquerydslmovies.models.Actor;
import com.github.alcbotta.javaqquerydslmovies.models.CrewMember;
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
        m = Movie.builder().name("star wars 1").actors(actorSet1).build();
        movieRepository.save(m);

        Actor actor3 = Actor.builder().name("christopher").build();
        Actor actor4 = Actor.builder().name("ewan").build();

        Set<Actor> actorSet2 = Stream.of(actor3, actor4).collect(Collectors.toCollection(HashSet::new));


        CrewMember member1 = CrewMember.builder().name("john").role("director").build();
        Set<CrewMember> crewMembersSet = Stream.of(member1).collect(Collectors.toCollection(HashSet::new));

        m = Movie.builder().name("star wars 2").actors(actorSet2).crew(crewMembersSet).build();


        movieRepository.save(m);
    }
}