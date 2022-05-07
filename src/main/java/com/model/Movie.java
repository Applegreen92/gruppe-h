package com.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

@DatabaseTable(tableName = "movie")
public class Movie {

    @DatabaseField(id = true)
    private int movieId;
    @DatabaseField(canBeNull = false)
    private String title;
    @DatabaseField(canBeNull = false)
    private String posterSrc;


    @DatabaseField(canBeNull = false)
    private int releaseDate;
    @DatabaseField(canBeNull = false)
    private int length;

    private String regisseur;

    private ArrayList<String> cast, genre, author;

    public Movie(){

    }

    public Movie(String title,String posterSrc, int date, int length,String regisseur,ArrayList genre,ArrayList author,ArrayList cast){
        this.title = title;
        this.posterSrc = posterSrc;
        this.releaseDate = date;
        this.length = length;
        this.regisseur = regisseur;
        this.author = author;
        this.genre = genre;
        this.cast = cast;

    }


    public String getTitle(){ return title; }
    public String getPosterSrc(){ return posterSrc; }
    public int getReleaseDate(){ return releaseDate; }
    public int getLength() { return length; }
    public String getRegisseur() { return regisseur; }
    public ArrayList<String> getAuthorList() { return this.author; }
    public ArrayList<String> getCastList() { return this.cast; }
    public ArrayList<String> getGenreList(){ return this.genre; }


    @Override
    public String toString() {
      return title+", "+posterSrc+", "+String.valueOf(releaseDate)+", "+String.valueOf(length)+", "+regisseur+", "+author+", "+cast+", "+genre;
    };

    // Equals the Parameters inside of the movie object with another movie object.
    public boolean equals(Movie movie) {
        if (this.title != movie.getTitle()) { return false; }
        if (this.posterSrc != movie.getPosterSrc()){ return false; }
        if (this.releaseDate != movie.getReleaseDate()){ return false; }
        if (this.length != movie.getLength()){ return false; }
        if (this.regisseur != movie.getRegisseur()){ return false; }


        for(int i = 0;i< cast.size();i++){
            for(int j = 0;j<movie.getCastList().size();j++){
                if(cast.get(i) == movie.getCastList().get(j)){
                    break;
                }
                return false;
            }
        }
        for(int i = 0;i< genre.size();i++){
            for(int j = 0;j<movie.getGenreList().size();j++){
                if(genre.get(i) == movie.getGenreList().get(j)){
                    break;
                }
                return false;
            }
        }
        for(int i = 0;i< author.size();i++){
            for(int j = 0;j<movie.getAuthorList().size();j++){
                if(cast.get(i) == movie.getAuthorList().get(j)){
                    break;
                }
                return false;
            }
        }

            return true;
    }
}
