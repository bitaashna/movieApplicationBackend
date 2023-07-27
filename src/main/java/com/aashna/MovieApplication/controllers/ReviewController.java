package com.aashna.MovieApplication.controllers;

import com.aashna.MovieApplication.payloads.ApiResponse;
import com.aashna.MovieApplication.payloads.ReviewDto;
import com.aashna.MovieApplication.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/movie/{movieId}/review")
    public ResponseEntity<ReviewDto> createComment(@RequestBody ReviewDto review,
                                                   @PathVariable Integer movieId) {

        ReviewDto createReview = this.reviewService.createReview(review, movieId);
        return new ResponseEntity<ReviewDto>(createReview, HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer reviewId) {
        this.reviewService.deleteReview(reviewId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Review Deleted", true), HttpStatus.OK);

    }
}
