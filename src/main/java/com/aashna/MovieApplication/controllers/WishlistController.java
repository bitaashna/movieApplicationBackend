package com.aashna.MovieApplication.controllers;

import com.aashna.MovieApplication.entities.Movie;
import com.aashna.MovieApplication.entities.Review;
import com.aashna.MovieApplication.entities.User;
import com.aashna.MovieApplication.entities.Wishlist;
import com.aashna.MovieApplication.exceptions.ResourceNotFoundException;
import com.aashna.MovieApplication.payloads.ApiResponse;
import com.aashna.MovieApplication.payloads.MovieDto;
import com.aashna.MovieApplication.payloads.ReviewDto;
import com.aashna.MovieApplication.repositories.MovieRepo;
import com.aashna.MovieApplication.repositories.UserRepo;
import com.aashna.MovieApplication.repositories.WishlistRepo;
import com.aashna.MovieApplication.services.WishlistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishListService;

    @Autowired
    private WishlistRepo wishlistRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/{userId}")
    public ResponseEntity<List<MovieDto>> getWishList(@PathVariable Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

        List<Wishlist> wishlist = wishListService.readWishList(userId);

        List<MovieDto> movies = new ArrayList<>();
        for (Wishlist wishlistItem : wishlist) {
            Movie movie = wishlistItem.getMovie();
            MovieDto movieDto = new MovieDto(movie);

            // Fetch the reviews associated with the movie
            Set<Review> reviews = movie.getReviews();

            // Map the reviews to ReviewDto objects and set them in the MovieDto
            Set<ReviewDto> reviewDtos = reviews.stream()
                    .map(review -> modelMapper.map(review, ReviewDto.class))
                    .collect(Collectors.toSet());

            movieDto.setReviews(reviewDtos);
            // Convert the poster Blob data to Base64 and set it in the MovieDto
            if (movie.getPoster() != null) {
                try {
                    String posterBase64 = convertBlobToBase64(movie.getPoster());
                    movieDto.setPosterBase64(posterBase64);
                } catch (SQLException e) {
                    throw new RuntimeException("Failed to convert Blob to Base64.", e);
                }
            }
            movies.add(movieDto);
        }

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
    private String convertBlobToBase64(Blob blob) throws SQLException {
        byte[] data = blob.getBytes(1, (int) blob.length());
        return Base64.getEncoder().encodeToString(data);
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

        // Check if the movie is already in the user's wishlist
        boolean movieAlreadyInWishlist = wishlistRepo.existsByUserUserIdAndMovieMovieId(userId, movieId);

        if (movieAlreadyInWishlist) {
            throw new IllegalArgumentException("Movie is already in the user's wishlist.");
        }

        Wishlist wishList = new Wishlist(user, movie);
        wishListService.createWishlist(wishList);
        return new ResponseEntity<>(new ApiResponse("Added to wishlist", true), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> removeFromWishlist(@RequestParam Integer userId,
                                                          @RequestParam Integer movieId) {
        wishListService.deleteFromWishlist(userId, movieId);
        return new ResponseEntity<>(new ApiResponse("Movie removed from wishlist", true), HttpStatus.OK);
    }

    public static MovieDto DtoFromMovie(Movie movie) {
        MovieDto movieDto = new MovieDto(movie);
        return movieDto;
    }
}