package com.controller;

import com.model.Crawler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CrawlerController extends SceneController implements Initializable {

    @FXML
    public TextField genres, startyear, endyear;

    public void startNow(ActionEvent actionEvent) throws IOException {
        Crawler autodownload = new Crawler();

        autodownload.getMoviesByGenre(genres.getText(),1,Integer.parseInt(startyear.getText()),Integer.parseInt(endyear.getText()));



    }

    public void abortNow(ActionEvent actionEvent) {switchToSceneWithStage("/Profil_Saiyan.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
