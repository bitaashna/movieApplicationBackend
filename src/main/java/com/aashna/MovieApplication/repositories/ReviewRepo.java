package com.aashna.MovieApplication.repositories;

import com.aashna.MovieApplication.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
}
