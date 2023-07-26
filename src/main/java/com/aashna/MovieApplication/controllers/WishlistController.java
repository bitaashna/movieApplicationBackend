package com.aashna.MovieApplication.controllers;

import com.aashna.MovieApplication.entities.Movie;
import com.aashna.MovieApplication.entities.User;
import com.aashna.MovieApplication.entities.Wishlist;
import com.aashna.MovieApplication.exceptions.ResourceNotFoundException;
import com.aashna.MovieApplication.payloads.ApiResponse;
import com.aashna.MovieApplication.payloads.MovieDto;
import com.aashna.MovieApplication.repositories.MovieRepo;
import com.aashna.MovieApplication.repositories.UserRepo;
import com.aashna.MovieApplication.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishListService;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MovieRepo movieRepo;


    @GetMapping("/{userId}")
    public ResponseEntity<List<MovieDto>> getWishList(@PathVariable Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

        List<Wishlist> body = wishListService.readWishList(userId);
        List<MovieDto> movies = new ArrayList<MovieDto>();
        for (Wishlist wishlist : body) {
            movies.add(DtoFromMovie(wishlist.getMovie()));
        }

        return new ResponseEntity<List<MovieDto>>(movies, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addWishList(@RequestParam("movieId") Integer movieId, @RequestParam("userId") Integer userId) {
        User user = this.userRepo.findById(userId).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse("User not found", false), HttpStatus.NOT_FOUND);
        }
        Movie movie = this.movieRepo.findById(movieId).orElse(null);
        if (movie == null) {
            return new ResponseEntity<>(new ApiResponse("Movie not found", false), HttpStatus.NOT_FOUND);
        }

        Wishlist wishList = new Wishlist(user, movie);
        wishListService.createWishlist(wishList);
        return new ResponseEntity<>(new ApiResponse("Added to wishlist", true), HttpStatus.CREATED);
    }

    public static MovieDto DtoFromMovie(Movie movie) {
        MovieDto movieDto = new MovieDto(movie);
        return movieDto;
    }
}