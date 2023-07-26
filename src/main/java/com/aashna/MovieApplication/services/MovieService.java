package com.aashna.MovieApplication.services;

import com.aashna.MovieApplication.entities.Movie;
import com.aashna.MovieApplication.payloads.MovieDto;

import java.util.List;

public interface MovieService {

    List<MovieDto> getAllMovies();
    List<MovieDto> getAllMoviesOfParticularActor(Integer actorId);


}
