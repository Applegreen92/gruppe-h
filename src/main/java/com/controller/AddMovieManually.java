package com.controller;

import com.model.Movie;
import javafx.*;
import java.util.Scanner;


public class AddMovieManually {
    Scanner scanner = new Scanner(System.in);

    private String title = scanner.next();

    private String posterSrc = scanner.next();

    private String genre = scanner.next();

    private int releaseDate = scanner.nextInt();

    private int length = scanner.nextInt();

    private String regisseur = scanner.next();

    private String author = scanner.next();

    private String cast = scanner.next();

    public Movie addMovieButton(){
        return new Movie(title,posterSrc,genre,releaseDate,length,regisseur,author,cast);
    }
}


