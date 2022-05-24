package com.example.application.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table
public class Movie {

    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "movie_sequence"
    )
    private int movieID;
    private String title;
    private String posterSrc;
    private int releaseDate;
    private int length;

    @OneToMany(mappedBy = "movie",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set<MoviePersonPartLink> moviePersonPartLink;

    public Movie() {

    }
    public Movie(int movieID, String title, String posterSrc, int releaseDate, int length) {
    }

    public Movie(String title, String toString, int parseInt, int convertLength, String toString1, ArrayList tempGenreArray, ArrayList tempWriterArray, ArrayList tempCastArray) {
    }

    public int getMovieID() {
        return movieID;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterSrc() {
        return posterSrc;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public int getLength() {
        return length;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosterSrc(String posterSrc) {
        this.posterSrc = posterSrc;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
