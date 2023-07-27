package com.aashna.MovieApplication.services.impl;

import com.aashna.MovieApplication.entities.Movie;
import com.aashna.MovieApplication.entities.Review;
import com.aashna.MovieApplication.exceptions.ResourceNotFoundException;
import com.aashna.MovieApplication.payloads.ReviewDto;
import com.aashna.MovieApplication.repositories.MovieRepo;
import com.aashna.MovieApplication.repositories.ReviewRepo;
import com.aashna.MovieApplication.services.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ReviewDto createReview(ReviewDto reviewDto, Integer movieId) {

        Movie movie = movieRepo.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "movie id", movieId));


        Review review = this.modelMapper.map(reviewDto, Review.class);

        review.setMovie(movie);


        Review savedReview = this.reviewRepo.save(review);

        return this.modelMapper.map(savedReview, ReviewDto.class);
    }

    @Override
    public void deleteReview(Integer reviewId) {

        Review rev = this.reviewRepo.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Review id", reviewId));

        this.reviewRepo.delete(rev);

    }

}
