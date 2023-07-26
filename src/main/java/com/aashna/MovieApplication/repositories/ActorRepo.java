package com.aashna.MovieApplication.repositories;

import com.aashna.MovieApplication.entities.Actor;
import com.aashna.MovieApplication.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorRepo extends JpaRepository<Actor, Integer> {

    @Query("SELECT a FROM Actor a JOIN a.movies m WHERE m = :movie")
    List<Actor> findActorsByMovie(@Param("movie") Movie movie);

}
