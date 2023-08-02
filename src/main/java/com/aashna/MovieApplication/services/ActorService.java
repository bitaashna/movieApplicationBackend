package com.aashna.MovieApplication.services;

import com.aashna.MovieApplication.payloads.ActorDto;

import java.util.List;

public interface ActorService {

    ActorDto getActorById(Integer actorId);
    List<ActorDto> getAllActorsOfParticularMovie(Integer movieId);

}
