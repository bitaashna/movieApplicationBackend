package com.aashna.MovieApplication.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="movies_table")
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(length = 100)
    private String title;

    private String poster;
    private Time duration;
    private String description;
    private String featuredYear;
    private String imdb;


    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "movie_actor",
            joinColumns ={ @JoinColumn(name = "movie_id", referencedColumnName = "movieId")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id",referencedColumnName = "id")})
    private Set<Actor> actors;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private List<Wishlist> wishListList;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();


}
