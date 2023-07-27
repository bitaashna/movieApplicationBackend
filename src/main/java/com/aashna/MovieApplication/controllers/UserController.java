package com.aashna.MovieApplication.controllers;

import com.aashna.MovieApplication.entities.Movie;
import com.aashna.MovieApplication.payloads.MovieDto;
import com.aashna.MovieApplication.payloads.UserSignInDto;
import com.aashna.MovieApplication.payloads.UserSignUpDto;
import com.aashna.MovieApplication.services.MovieService;
import com.aashna.MovieApplication.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MovieService movieService;

    @GetMapping
    @RequestMapping(value = "/api/create", method = RequestMethod.POST)
    public ResponseEntity<UserSignUpDto> registerUser(@RequestBody UserSignUpDto userSignUpDto) {
        UserSignUpDto createdUser = userService.createUser(userSignUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<UserSignInDto> login(
            @RequestBody UserSignInDto userLogin) {
        UserSignInDto user2 = userService.loginUser(userLogin.getEmail(),userLogin.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(user2);
    }

    public MovieDto movieToDto(Movie movie) {

        MovieDto movieDto = this.modelMapper.map(movie, MovieDto.class);

        return movieDto;
    }







}
