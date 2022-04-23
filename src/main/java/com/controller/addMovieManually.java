package com.controller;

import com.example.gruppeh.HelloApplication;
import com.model.Movie;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addMovieManually extends Application {

    @FXML
    public TextField textTitle;
    @FXML
    public TextField textGenre;
    @FXML
    public TextField textPosterSrc;
    @FXML
    public TextField textReleaseDate;
    @FXML
    public TextField textMovielength;
    @FXML
    public TextField textRegisseur;
    @FXML
    public TextField textAuthor;
    @FXML
    public TextField textCast;


    @FXML
    //Checks if the Film allready exists, if not,create a new movie Object using the Parameter
    //given from the Textfields and pushes it into the Database. If it allready exists take the
    //Movie Object off the database, change the variables and pushes it back in.
    public boolean save(){
        String title = textTitle.getText().toString();
        String genre = textGenre.getText().toString();
        String posterSrc = textPosterSrc.getText().toString();
        int releaseDate = Integer.parseInt(textReleaseDate.getText());
        int movieLength = Integer.parseInt(textMovielength.getText());
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


    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addFilmGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Here you can manually put in movies");
        stage.setScene(scene);
        stage.show();


    }
    public static void main(String[] args) {launch(args);}
}
