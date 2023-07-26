package com.aashna.MovieApplication.services.impl;

import com.aashna.MovieApplication.entities.User;
import com.aashna.MovieApplication.exceptions.AuthenticationFailException;
import com.aashna.MovieApplication.exceptions.EmailAlreadyExistsException;
import com.aashna.MovieApplication.payloads.UserSignInDto;
import com.aashna.MovieApplication.payloads.UserSignUpDto;
import com.aashna.MovieApplication.repositories.MovieRepo;
import com.aashna.MovieApplication.repositories.UserRepo;
import com.aashna.MovieApplication.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    MovieRepo movieRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserSignUpDto createUser(UserSignUpDto userSignUpDto) {
        String email = userSignUpDto.getEmail();

        // Check if the email already exists in the database
        User existingUser = userRepo.findByEmail(email);
        if (existingUser != null) {
            // Email already in use, return an error response
            throw new EmailAlreadyExistsException("Email is already in use");
        }
        User user = this.dtoToUserSignUp(userSignUpDto);
        User savedUser = this.userRepo.save(user);

        return this.userToDtoSignUp(savedUser);
    }

    @Override
    public UserSignInDto loginUser(String email, String password) {
        User user = userRepo.findByEmail(email);

        if (!user.getPassword().equals(password)){
            throw new AuthenticationFailException("Incorrect password");
        }

        return this.userToDtoSignIn(this.userRepo.findUserByUsernameAndPassword(email, password));
    }

    public User dtoToUserSignUp(UserSignUpDto userSignUpDto) {

        User user = this.modelMapper.map(userSignUpDto, User.class);

        return user;
    }

    public UserSignUpDto userToDtoSignUp(User user) {

        UserSignUpDto userSignUpDto = this.modelMapper.map(user, UserSignUpDto.class);

        return userSignUpDto;
    }

    public UserSignInDto userToDtoSignIn(User user) {

        UserSignInDto userSignInDto = this.modelMapper.map(user, UserSignInDto.class);

        return userSignInDto;
    }
}
