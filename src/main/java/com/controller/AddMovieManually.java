package com.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Movie;
import com.model.MyClient;
import com.view.addMovieApplication;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.controller.SceneController;

public class AddMovieManually extends SceneController{

    MyClient client;
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

    ArrayList<String> genreList = new ArrayList<>();
    ArrayList<String> authorList = new ArrayList<>();
    ArrayList<String> castList = new ArrayList<>();

    public AddMovieManually() throws IOException {
    }

    public ArrayList<String> getAuthorList() {
        return authorList;
    }

    public ArrayList<String> getCastList() {
        return castList;
    }

    public ArrayList<String> getGenreList() {
        return genreList;
    }

    public void setAuthorList(ArrayList<String> authorList) {
        this.authorList = authorList;
    }

    public void setCastList(ArrayList<String> castList) {
        this.castList = castList;
    }

    public void setGenreList(ArrayList<String> genreList) {
        this.genreList = genreList;
    }

    @FXML
    public void genreAdd(){
        genreList.add(textGenre.getText());
        textGenre.clear();
    }
    @FXML
    public void authorAdd(){
        authorList.add(textAuthor.getText());
        textAuthor.clear();
    }
    @FXML
    public void castAdd(){
        castList.add(textCast.getText());
        textCast.clear();
    }

    @FXML
    //Checks if the Film allready exists, if not,create a new movie Object using the Parameter
    //given from the Textfields and pushes it into the Database. If it allready exists take the
    //Movie Object off the database, change the variables and pushes it back in.
    public void save()  {
        String title = textTitle.getText();

        String posterSrc = textPosterSrc.getText();
        int releaseDate = Integer.parseInt(textReleaseDate.getText());
        int movieLength = Integer.parseInt(textMovieLength.getText());
        String regisseur = textRegisseur.getText();
        ArrayList<String> genre = this.genreList;
        ArrayList<String> author = this.authorList;
        ArrayList<String> cast = this.castList;


        Movie movie = new Movie(title,posterSrc,releaseDate,movieLength,regisseur,genreList,authorList,castList);
        genreList.add(this.textGenre.getText());
        authorList.add(this.textAuthor.getText());
        castList.add(this.textCast.getText());
        try {
            String jsonMovie = new ObjectMapper().writeValueAsString(movie);
            MyClient.printwriter.println(jsonMovie);
            MyClient.printwriter.flush();
            textCast.clear();
            textAuthor.clear();
            textRegisseur.clear();
            textMovieLength.clear();
            textPosterSrc.clear();
            textTitle.clear();
            textGenre.clear();
            textReleaseDate.clear();
        }catch(Exception e){
            e.printStackTrace();

        }


    }
    @FXML
    //Goes back to the last page without doing anything
    public void abort(){
        switchToSceneWithStage("/Profil_Saiyan.fxml");
    }



}
