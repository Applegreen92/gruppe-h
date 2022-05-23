package com.example.application.data.entity;

import javax.persistence.*;

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
    private int genreID;
    private String genre;

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
