package com.aashna.MovieApplication.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="wishlist_table")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")
    private User user;

    @Column(name="createdDate")
    private Date createdDate;

    @ManyToOne()
    @JoinColumn(name = "movieId")
    private Movie movie;

    public Wishlist() {
    }

    public Wishlist(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
        this.createdDate = new Date();
    }
}
