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

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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

        return convertActorsToActorDtos(actors);
    }

    // Convert Blob to Base64 directly using Base64.getEncoder()
    private String convertBlobToBase64(Blob blob) throws SQLException {
        byte[] data = blob.getBytes(1, (int) blob.length());
        return Base64.getEncoder().encodeToString(data);
    }

    // Helper method to convert actors to actorDtos and handle Blob to Base64 conversion
    private List<ActorDto> convertActorsToActorDtos(List<Actor> actors) {
        if (actors != null && !actors.isEmpty()) {
            List<ActorDto> actorDtos = new ArrayList<>();
            for (Actor actor : actors) {
                ActorDto actorDto = modelMapper.map(actor, ActorDto.class);

                // Convert the image Blob data to Base64 and set it in the ActorDto
                if (actor.getImg() != null) {
                    try {
                        String imageBase64 = convertBlobToBase64(actor.getImg());
                        actorDto.setImgBase64(imageBase64);
                    } catch (SQLException e) {
                        throw new RuntimeException("Failed to convert Blob to Base64.", e);
                    }
                }

                actorDtos.add(actorDto);
            }
            return actorDtos;
        }
        return new ArrayList<>();
    }
}
