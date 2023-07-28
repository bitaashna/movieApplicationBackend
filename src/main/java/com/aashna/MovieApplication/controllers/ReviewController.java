package com.aashna.MovieApplication.controllers;

import com.aashna.MovieApplication.entities.User;
import com.aashna.MovieApplication.payloads.ApiResponse;
import com.aashna.MovieApplication.payloads.ReviewDto;
import com.aashna.MovieApplication.repositories.UserRepo;
import com.aashna.MovieApplication.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/movie/{movieId}/review")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto,
                                                  @PathVariable Integer movieId,
                                                  @RequestParam Integer userId) {
        String[] userNames = getUserNamesById(userId);
        if (userNames == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        String firstName = userNames[0];
        String lastName = userNames[1];

        reviewDto.setFirstName(firstName);
        reviewDto.setLastName(lastName);

        ReviewDto createdReview = this.reviewService.createReview(reviewDto, movieId, userId);

        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer reviewId) {
        this.reviewService.deleteReview(reviewId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Review Deleted", true), HttpStatus.OK);

    }
    private String[] getUserNamesById(Integer userId) {
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            return new String[]{firstName, lastName};
        } else {
            return null; // User not found with the provided userId
        }
    }

}
