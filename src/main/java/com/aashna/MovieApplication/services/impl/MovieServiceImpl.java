package com.aashna.MovieApplication.services.impl;

import com.aashna.MovieApplication.entities.Actor;
import com.aashna.MovieApplication.entities.Movie;
import com.aashna.MovieApplication.exceptions.ResourceNotFoundException;
import com.aashna.MovieApplication.payloads.MovieDto;
import com.aashna.MovieApplication.repositories.ActorRepo;
import com.aashna.MovieApplication.repositories.MovieRepo;
import com.aashna.MovieApplication.repositories.UserRepo;
import com.aashna.MovieApplication.services.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ActorRepo actorRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public List<MovieDto> getAllMoviesOfParticularActor(Integer actorId) {

        Actor actor = this.actorRepo.findById(actorId)
                .orElseThrow(() -> new ResourceNotFoundException("Actor", "id", actorId));

        List<Movie> movies = this.movieRepo.findMoviesByActor(actor);

        List<MovieDto> movieDtos = movies.stream().map((movie) -> this.modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());

        return movieDtos;
    }

    @Override
    public MovieDto getMovieById(Integer movieId) {

        Movie movie = this.movieRepo.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", movieId));

        return this.modelMapper.map(movie, MovieDto.class);
    }


    @Override
    public List<MovieDto> getAllMovies() {

        List<Movie> movies = this.movieRepo.findAll();

        List<MovieDto> movieDtos = movies.stream().map(movie -> this.movieToDto(movie)).collect(Collectors.toList());

        return movieDtos;
    }
    public MovieDto movieToDto(Movie movie) {

        MovieDto movieDto = this.modelMapper.map(movie, MovieDto.class);

        return movieDto;
    }
}
