package com.aashna.MovieApplication.controllers;

import com.aashna.MovieApplication.payloads.ActorDto;
import com.aashna.MovieApplication.services.ActorService;
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
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/movie/{movieId}/actors")
    public ResponseEntity<List<ActorDto>> getAllActorsOfParticularMovie(@PathVariable Integer movieId) {
        List<ActorDto> actors = this.actorService.getAllActorsOfParticularMovie(movieId);
        return new ResponseEntity<List<ActorDto>>(actors, HttpStatus.OK);
    }

    @GetMapping("/actor/{actorId}")
    public ResponseEntity<ActorDto> getActorById(@PathVariable Integer actorId) {
        ActorDto actorDto = this.actorService.getActorById(actorId);
        return new ResponseEntity<ActorDto>(actorDto, HttpStatus.OK);
    }
}
