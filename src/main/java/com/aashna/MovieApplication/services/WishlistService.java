package com.aashna.MovieApplication.services;

import com.aashna.MovieApplication.entities.Movie;
import com.aashna.MovieApplication.entities.User;
import com.aashna.MovieApplication.entities.Wishlist;
import com.aashna.MovieApplication.exceptions.ResourceNotFoundException;
import com.aashna.MovieApplication.repositories.MovieRepo;
import com.aashna.MovieApplication.repositories.UserRepo;
import com.aashna.MovieApplication.repositories.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WishlistService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MovieRepo movieRepo;

    private final WishlistRepo wishlistRepository;

    public WishlistService(WishlistRepo wishListRepository) {
        this.wishlistRepository = wishListRepository;
    }

    public void createWishlist(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }

    public List<Wishlist> readWishList(Integer userId) {
        return wishlistRepository.findAllByUserUserIdOrderByCreatedDateDesc(userId);
    }

    public void deleteFromWishlist(Integer userId, Integer movieId) {
          User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

        Movie movie = this.movieRepo.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", movieId));


        Optional<Wishlist> wishlistItemOptional = wishlistRepository.findFirstByUserUserIdAndMovieMovieId(userId, movieId);
        if (wishlistItemOptional.isPresent()) {
            Wishlist wishlistItem = wishlistItemOptional.get();
            wishlistRepository.delete(wishlistItem);
        }
    }
}
