package com.model;

import java.util.Date;

public class Movie {

    public Movie(String title, String genre,String posterSrc, int date){
        this.title = title;
        this.posterSrc = posterSrc;
        this.genre = genre;
        this.releaseDate = date;
     }


    private String title;
    private String posterSrc;
    private String genre;
    private int releaseDate;

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






}
