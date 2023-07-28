package com.aashna.MovieApplication.repositories;

import com.aashna.MovieApplication.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {

    List<Wishlist> findAllByUserUserIdOrderByCreatedDateDesc(Integer userId);

    @Query("SELECT COUNT(w) > 0 FROM Wishlist w WHERE w.user.userId = :userId AND w.movie.movieId = :movieId")
    boolean existsByUserUserIdAndMovieMovieId(@Param("userId") Integer userId, @Param("movieId") Integer movieId);

    Optional<Wishlist> findFirstByUserUserIdAndMovieMovieId(Integer userId, Integer movieId);
}
