package com.aashna.MovieApplication.services.impl;

import com.aashna.MovieApplication.entities.Actor;
import com.aashna.MovieApplication.entities.Movie;
import com.aashna.MovieApplication.exceptions.ResourceNotFoundException;
import com.aashna.MovieApplication.payloads.ActorDto;
import com.aashna.MovieApplication.repositories.ActorRepo;
import com.aashna.MovieApplication.repositories.MovieRepo;
import com.aashna.MovieApplication.services.ActorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {
    @Autowired
    private ActorRepo actorRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MovieRepo movieRepo;

    public ActorServiceImpl(ActorRepo actorRepo) {
        this.actorRepo = actorRepo;
    }

    @Override
    public List<ActorDto> getAllActorsOfParticularMovie(Integer movieId) {

        Movie movie = this.movieRepo.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));

        List<Actor> actors = this.actorRepo.findActorsByMovie(movie);

        List<ActorDto> actorDtos = actors.stream().map((actor) -> this.modelMapper.map(actor, ActorDto.class))
                .collect(Collectors.toList());

        return actorDtos;
    }


}
