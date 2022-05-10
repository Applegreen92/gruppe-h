package com.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

@JsonAutoDetect
public class Movie {

    private int movieId;
    private String title;

    private String posterSrc;


    private int releaseDate;

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
    public ArrayList<String> getListAuthor() { return this.author; }
    public ArrayList<String> getListCast() { return this.cast; }
    public ArrayList<String> getListGenre(){ return this.genre; }

    public void setListAuthor(ArrayList<String> author) {
        this.author = author;
    }

    public void setListCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public void setListGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setPosterSrc(String posterSrc) {
        this.posterSrc = posterSrc;
    }

    public void setRegisseur(String regisseur) {
        this.regisseur = regisseur;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
            for(int j = 0;j<movie.getListCast().size();j++){
                if(cast.get(i) == movie.getListCast().get(j)){
                    break;
                }
                return false;
            }
        }
        for(int i = 0;i< genre.size();i++){
            for(int j = 0;j<movie.getListGenre().size();j++){
                if(genre.get(i) == movie.getListGenre().get(j)){
                    break;
                }
                return false;
            }
        }
        for(int i = 0;i< author.size();i++){
            for(int j = 0;j<movie.getListAuthor().size();j++){
                if(cast.get(i) == movie.getListAuthor().get(j)){
                    break;
                }
                return false;
            }
        }

            return true;
    }

}
