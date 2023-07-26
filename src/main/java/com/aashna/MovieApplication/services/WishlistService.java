package com.aashna.MovieApplication.services;

import com.aashna.MovieApplication.entities.Wishlist;
import com.aashna.MovieApplication.repositories.WishlistRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WishlistService {
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
}
