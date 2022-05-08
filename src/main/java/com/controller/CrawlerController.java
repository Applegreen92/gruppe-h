package com.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class CrawlerController extends SceneController implements Initializable {

    @FXML
    public TextField genres, startyear, endyear;
    
    public void startNow(ActionEvent actionEvent) {
    }

    public void abortNow(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
