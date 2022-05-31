package com.example.application.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Genre {
    @Id
    @SequenceGenerator(
            name = "genre_sequence",
            sequenceName = "genre_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "genre_sequence"
    )
    public int genreID;
    public String genre;
    @ManyToMany(mappedBy = "genreList",cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    public List<Movie> movies = new ArrayList<>();

    public Genre() {
    }

    public Genre(String genre){
        super();
        this.genre = genre;
    }

    public Genre(int genreID, String genre) {
        this.genreID = genreID;
        this.genre = genre;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
