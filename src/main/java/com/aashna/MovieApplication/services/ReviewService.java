package com.aashna.MovieApplication.services;

import com.aashna.MovieApplication.payloads.ReviewDto;

public interface ReviewService {


    ReviewDto createReview(ReviewDto reviewDto, Integer movieId, Integer userId);

}
