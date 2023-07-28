package com.aashna.MovieApplication.payloads;

import com.aashna.MovieApplication.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto implements Serializable {
    private static final long serialVersionUID = 4439114469417994311L;

    private Integer movieId;

    @NotEmpty
    @Size(min = 3, message = "Movie Name must be of 3 characters!!")
    private String title;

    private String poster;

    private Time duration;

    private String description;

    private String featuredYear;

    private String imdb;

    private Set<ReviewDto> reviews = new HashSet<>();

    public MovieDto(Movie movie) {
        this.setMovieId(movie.getMovieId());
        this.setTitle(movie.getTitle());
        this.setImdb(movie.getImdb());
        this.setDuration(movie.getDuration());
        this.setDescription(movie.getDescription());
        this.setFeaturedYear(movie.getFeaturedYear());
        this.setPoster(movie.getPoster());

    }
}
