package com.aashna.MovieApplication.repositories;

import com.aashna.MovieApplication.entities.Actor;
import com.aashna.MovieApplication.entities.Movie;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Integer> {

    @Query("SELECT m FROM Movie m JOIN m.actors a WHERE a = :actor")
    List<Movie> findMoviesByActor(@Param("actor") Actor actor);


}
