package com.aashna.MovieApplication.repositories;

import com.aashna.MovieApplication.entities.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {

    List<Wishlist> findAllByUserUserIdOrderByCreatedDateDesc(Integer userId);


}
