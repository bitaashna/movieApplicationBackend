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

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

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

        return convertMoviesToMovieDtos(movies);
    }

    @Override
    public MovieDto getMovieById(Integer movieId) {

        Optional<Movie> movieOptional = movieRepo.findById(movieId);
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();

            // Convert the poster Blob data to Base64
            String posterBase64 = null;
            if (movie.getPoster() != null) {
                try {
                    posterBase64 = convertBlobToBase64(movie.getPoster());
                } catch (SQLException e) {
                    throw new RuntimeException("Failed to convert Blob to Base64.", e);
                }
            }

            // Create a new MovieDto object and set the Base64 data
            MovieDto movieDto = modelMapper.map(movie, MovieDto.class);
            movieDto.setPosterBase64(posterBase64);

            return movieDto;
        }
        return null;
    }

    private String convertBlobToBase64(Blob blob) throws SQLException {
        byte[] data = blob.getBytes(1, (int) blob.length());
        return Base64.getEncoder().encodeToString(data);
    }


    @Override
    public List<MovieDto> getAllMovies() {

        List<Movie> movies = this.movieRepo.findAll();

        if (movies != null && !movies.isEmpty()) {
            List<MovieDto> movieDtos = new ArrayList<>();
            for (Movie movie : movies) {
                MovieDto movieDto = modelMapper.map(movie, MovieDto.class);

                // Convert the poster Blob data to Base64 and set it in the MovieDto
                if (movie.getPoster() != null) {
                    try {
                        String posterBase64 = convertBlobToBase64(movie.getPoster());
                        movieDto.setPosterBase64(posterBase64);
                    } catch (SQLException e) {
                        throw new RuntimeException("Failed to convert Blob to Base64.", e);
                    }
                }

                movieDtos.add(movieDto);
            }
            return movieDtos;
        }
        return new ArrayList<>();
    }
    public MovieDto movieToDto(Movie movie) {

        MovieDto movieDto = this.modelMapper.map(movie, MovieDto.class);

        return movieDto;
    }

    // Helper method to convert movies to movieDtos and handle Blob to Base64 conversion
    private List<MovieDto> convertMoviesToMovieDtos(List<Movie> movies) {
        if (movies != null && !movies.isEmpty()) {
            List<MovieDto> movieDtos = new ArrayList<>();
            for (Movie movie : movies) {
                MovieDto movieDto = modelMapper.map(movie, MovieDto.class);

                // Convert the poster Blob data to Base64 and set it in the MovieDto
                if (movie.getPoster() != null) {
                    try {
                        String posterBase64 = convertBlobToBase64(movie.getPoster());
                        movieDto.setPosterBase64(posterBase64);
                    } catch (SQLException e) {
                        throw new RuntimeException("Failed to convert Blob to Base64.", e);
                    }
                }

                movieDtos.add(movieDto);
            }
            return movieDtos;
        }
        return new ArrayList<>();
    }
}

