package com.aashna.MovieApplication.services;

import com.aashna.MovieApplication.payloads.ActorDto;

import java.util.List;

public interface ActorService {
    List<ActorDto> getAllActorsOfParticularMovie(Integer movieId);

}
