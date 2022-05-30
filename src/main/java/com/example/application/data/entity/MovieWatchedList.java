package com.example.application.data.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class MovieWatchedList {
    @Id
    @SequenceGenerator(
            name = "movieWatchedList_sequence",
            sequenceName = "movieWatchedList_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movieWatchedList_sequence"
    )
    private int MovieWatchedListID;

    @ManyToOne
    @JoinColumn(name = "movieID")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "ID")
    private User user;

    public MovieWatchedList(Movie movie, User user) {
        this.movie = movie;
        this.user = user;
    }

    public MovieWatchedList() {

    }


    public int getMovieWatchedListID() {
        return MovieWatchedListID;
    }

    public void setMovieWatchedListID(int movieWatchedListID) {
        MovieWatchedListID = movieWatchedListID;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
