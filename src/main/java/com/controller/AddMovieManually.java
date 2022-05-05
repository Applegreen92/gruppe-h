package com.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import com.controller.SceneController;

public class AddMovieManually extends SceneController {

    @FXML
    public TextField textTitle;
    @FXML
    public TextField textGenre;
    @FXML
    public TextField textPosterSrc;
    @FXML
    public TextField textReleaseDate;
    @FXML
    public TextField textMovieLength;
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
    public boolean save()  {
        String title = textTitle.getText();
        String genre = textGenre.getText();
        String posterSrc = textPosterSrc.getText();
        int releaseDate = Integer.parseInt(textReleaseDate.getText());
        int movieLength = Integer.parseInt(textMovieLength.getText());
        String regisseur = textRegisseur.getText();
        String author = textAuthor.getText();
        String cast = textCast.getText();


        Movie movie = new Movie(title,genre,posterSrc,releaseDate,movieLength,regisseur,author,cast);
        System.out.println(movie.toString());
        try {
            String jsonMovie = new ObjectMapper().writeValueAsString(movie);
            Socket socket = new Socket("localhost", 10);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            pw.println(jsonMovie);
            pw.flush();
            pw.close();
        }catch(Exception e){
            System.out.println("Sending Movie to Server failed ...");
            return false;
        }

        return true;
    }
    @FXML
    //Goes back to the last page without doing anything
    public void abort(){
        switchToSceneWithStage("Login.fxml");
    }



}
