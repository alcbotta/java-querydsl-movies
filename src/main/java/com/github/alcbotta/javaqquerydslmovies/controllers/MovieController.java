package com.github.alcbotta.javaqquerydslmovies.controllers;

import com.github.alcbotta.javaqquerydslmovies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping(path = "/api/v1")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/movies/{id}")
    public @ResponseBody ResponseEntity<Object> findById( @PathVariable Long id) {
        return new ResponseEntity<Object>(movieService.findById(id), HttpStatus.OK);
    }
}
