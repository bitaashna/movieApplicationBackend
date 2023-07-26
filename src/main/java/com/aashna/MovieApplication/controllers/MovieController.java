package com.aashna.MovieApplication.controllers;

import com.aashna.MovieApplication.payloads.MovieDto;
import com.aashna.MovieApplication.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movie/")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.ok(this.movieService.getAllMovies());
    }

    @GetMapping("/actor/{actorId}/movies")
    public ResponseEntity<List<MovieDto>> getAllMoviesOfParticularActor(@PathVariable Integer actorId) {
        List<MovieDto> movies = this.movieService.getAllMoviesOfParticularActor(actorId);
        return new ResponseEntity<List<MovieDto>>(movies, HttpStatus.OK);
    }

}
