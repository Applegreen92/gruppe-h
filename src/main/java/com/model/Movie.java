package com.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "movie")
public class Movie {

    @DatabaseField(id = true)
    private int movieId;
    @DatabaseField(canBeNull = false)
    private String title;
    @DatabaseField(canBeNull = false)
    private String posterSrc;
    @DatabaseField(canBeNull = false)
    private String genre;
    @DatabaseField(canBeNull = false)
    private int releaseDate;
    @DatabaseField(canBeNull = false)
    private int length;
    @DatabaseField(canBeNull = false)
    private String regisseur;
    @DatabaseField(canBeNull = false)
    private String author;
    @DatabaseField(canBeNull = false)
    private String cast;

    public Movie(){

    }

    public Movie(String title, String genre,String posterSrc, int date, int length,String regisseur,String author,String cast){
        this.title = title;
        this.posterSrc = posterSrc;
        this.genre = genre;
        this.releaseDate = date;
        this.length = length;
        this.regisseur = regisseur;
        this.author = author;
        this.cast = cast;

    }


    public String getTitle(){ return title; }
    public String getGenre(){ return genre; }
    public String getPosterSrc(){ return posterSrc; }
    public int getReleaseDate(){ return releaseDate; }
    public int getLength() { return length; }
    public String getRegisseur() { return regisseur; }
    public String getAuthor() { return author; }
    public String getCast() { return cast; }


    @Override
    public String toString() {
      return title+", "+genre+", "+String.valueOf(releaseDate)+", "+String.valueOf(length)+", "+regisseur+", "+author+", "+cast+", "+posterSrc;
    };

    // Equals the Parameters inside of the movie object with another movie object.
    public boolean equals(Movie movie) {
        if(this.author != movie.getAuthor()){
            return false;
        }else if (this.cast != movie.getCast()){
            return false;
        }else if (this.title != movie.getTitle()){
            return false;
        }else if (this.genre != movie.getGenre()){
            return false;
        }else if (this.posterSrc != movie.getPosterSrc()){
            return false;
        }else if (this.releaseDate != movie.getReleaseDate()){
            return false;
        }else if (this.length != movie.getLength()){
            return false;
        }else if (this.regisseur != movie.getRegisseur()){
            return false;
        }else{
            return true;
        }
    }
}
