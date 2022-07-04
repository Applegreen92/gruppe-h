package com.example.application.data.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "watchedmovies")
public class WatchedMovies {

    @Id
    @SequenceGenerator(
            name = "watchedmovies_sequence",
            sequenceName = "watchedmovies_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "watchedmovies_sequence"
    )
    public int watchedMoviesID;

    @ManyToOne
    @JoinColumn(name = "movieID")
    private Movie movieUserWatched;

    @ManyToOne
    @JoinColumn(name = "id")
    private User userWatchedID;

    @Temporal(TemporalType.DATE)
    Date creationDateTime;

    public WatchedMovies() {
    }

    public Movie getMovieUserWatched() {
        return movieUserWatched;
    }

    public void setMovieUserWatched(Movie movieUserWatched) {
        this.movieUserWatched = movieUserWatched;
    }

    public User getUserWatchedID() {
        return userWatchedID;
    }

    public void setUserWatchedID(User userWatchedID) {
        this.userWatchedID = userWatchedID;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public WatchedMovies(Movie movie, User user, Date creationDateTime) {
        this.movieUserWatched = movie;
        this.userWatchedID = user;
        this.creationDateTime = creationDateTime;
    }
}
