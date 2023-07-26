package com.aashna.MovieApplication.services;

import com.aashna.MovieApplication.payloads.UserSignInDto;
import com.aashna.MovieApplication.payloads.UserSignUpDto;


public interface UserService {

    public UserSignInDto loginUser(String email, String password);
    public UserSignUpDto createUser(UserSignUpDto user);


}
