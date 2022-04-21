package com.model;

import java.util.Date;

public class Movie {

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


    private String title;
    private String posterSrc;
    private String genre;
    private int releaseDate;
    private int length;
    private String regisseur;
    private String author;
    private String cast;


    public String getTitle(){
        return title;
    }
    public String getGenre(){
        return genre;
    }
    public String getPosterSrc(){
        return posterSrc;
    }
    public int getReleaseDate(){
        return releaseDate;
    }
    public int getLength() { return length; }
    public String getRegisseur() { return regisseur; }
    public String getAuthor() { return author; }
    public String getCast() { return cast; }
}
