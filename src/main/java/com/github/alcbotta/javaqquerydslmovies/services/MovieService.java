package com.github.alcbotta.javaqquerydslmovies.services;


import com.github.alcbotta.javaqquerydslmovies.dto.movie.find.MovieDTO;
import com.github.alcbotta.javaqquerydslmovies.models.Movie;
import com.github.alcbotta.javaqquerydslmovies.repositories.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Movie save (Movie movie){
        return movieRepository.save(movie);
    }

    public Optional<Movie> findById (Long id){
        return movieRepository.findById(id);
    }

    public <T> T convertToDTO(Movie m, Class<T> type) {
        return modelMapper.map(m, type);
    }

    public <T> Optional<T> findByIdDto (Long id,  Class<T> type){
        Optional <Movie> opt = movieRepository.findById(id);
        if (opt.isPresent()){
            return Optional.of(convertToDTO(opt.get(), type));
        }
        return Optional.empty();
    }

    public Optional<MovieDTO> findByIdDto (Long id){
        return findByIdDto (id, MovieDTO.class);
    }
}
