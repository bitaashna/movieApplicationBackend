package com.aashna.MovieApplication.services;

import com.aashna.MovieApplication.payloads.MovieDto;

import java.util.List;

public interface MovieService {

    MovieDto getMovieById(Integer movieId);

    List<MovieDto> getAllMovies();
    List<MovieDto> getAllMoviesOfParticularActor(Integer actorId);


}
