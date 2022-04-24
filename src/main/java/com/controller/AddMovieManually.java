package com.controller;

import com.model.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddMovieManually {

    @FXML
    private TextField textTitle;
    @FXML
    private TextField textGenre;
    @FXML
    private TextField textPosterSrc;
    @FXML
    private TextField textReleaseDate;
    @FXML
    private TextField textMovieLength;
    @FXML
    private TextField textRegisseur;
    @FXML
    private TextField textAuthor;
    @FXML
    private TextField textCast;


    @FXML
    //Checks if the Film allready exists, if not,create a new movie Object using the Parameter
    //given from the Textfields and pushes it into the Database. If it allready exists take the
    //Movie Object off the database, change the variables and pushes it back in.
    public boolean save(){
        String title = textTitle.getText().toString();
        String genre = textGenre.getText().toString();
        String posterSrc = textPosterSrc.getText().toString();
        int releaseDate = Integer.parseInt(textReleaseDate.getText());
        int movieLength = Integer.parseInt(textMovieLength.getText());
        String regisseur = textRegisseur.getText();
        String author = textAuthor.getText();
        String cast = textCast.getText();


        Movie movie = new Movie(title,genre,posterSrc,releaseDate,movieLength,regisseur,author,cast);




        System.out.println(movie.toString());
        return false;
    }
    @FXML
    //Goes back to the last page without doing anything
    public void abort(){
    }



}
