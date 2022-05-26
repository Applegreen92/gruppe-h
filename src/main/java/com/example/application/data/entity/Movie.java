package com.example.application.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    @Transient
    ArrayList<Genre> genreList = new ArrayList<>();
    @Transient
    String personDirector;
    @Transient
    ArrayList<Person> personAuthorList = new ArrayList<>();
    @Transient
    ArrayList<Person> personCastList = new ArrayList<>();

    @OneToMany(mappedBy = "movie",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set<MoviePersonPartLink> moviePersonPartLink;

    public Movie(String title, String toString, int parseInt, int convertLength, ArrayList tempGenreArray, String personDirector, ArrayList tempWriterArray, ArrayList tempCastArray) {
        this.title = title;
        this.posterSrc = posterSrc;
        this.releaseDate = releaseDate;
        this.length = length;
        this.genreList = genreList;
        this.personDirector = personDirector;
        this.personAuthorList = personAuthorList;
        this.personCastList = personCastList;
        this.moviePersonPartLink = moviePersonPartLink;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieID=" + movieID +
                ", title='" + title + '\'' +
                ", posterSrc='" + posterSrc + '\'' +
                ", releaseDate=" + releaseDate +
                ", length=" + length +
                ", moviePersonPartLink=" + moviePersonPartLink +
                '}';
    }

    public ArrayList<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(ArrayList<Genre> genreList) {
        this.genreList = genreList;
    }

    public String getPersonDirector() {
        return personDirector;
    }

    public void setPersonDirector(String personDirector) {
        this.personDirector = personDirector;
    }

    public ArrayList<Person> getPersonAuthorList() {
        return personAuthorList;
    }

    public void setPersonAuthorList(ArrayList<Person> personAuthorList) {
        this.personAuthorList = personAuthorList;
    }

    public ArrayList<Person> getPersonCastList() {
        return personCastList;
    }

    public void setPersonCastList(ArrayList<Person> personCastList) {
        this.personCastList = personCastList;
    }

    public Movie() {

    }
    public Movie(int movieID, String title, String posterSrc, int releaseDate, int length) {
        this.movieID = movieID;
        this.title = title;
        this.posterSrc = posterSrc;
        this.releaseDate = releaseDate;
        this.length = length;
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
