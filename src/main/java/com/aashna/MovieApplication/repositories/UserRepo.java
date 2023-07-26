package com.aashna.MovieApplication.repositories;

import com.aashna.MovieApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User findUserByUsernameAndPassword(
            @Param("email") String email,
            @Param("password") String password
    );

    User findByEmail(String email);





}
