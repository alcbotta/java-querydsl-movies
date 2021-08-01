package com.github.alcbotta.javaqquerydslmovies.services;


import com.github.alcbotta.javaqquerydslmovies.models.Movie;
import com.github.alcbotta.javaqquerydslmovies.repositories.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie save (Movie movie){
        return movieRepository.save(movie);
    }

    public Optional<Movie> findById (Long id){
        return movieRepository.findById(id);
    }
}
