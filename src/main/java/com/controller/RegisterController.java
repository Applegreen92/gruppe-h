package com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.MyClient;
import com.model.User;
import com.testPackage.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class RegisterController extends SceneController implements Initializable {
    @FXML
    public TextField username, name, surname, mail;

    @FXML
    public PasswordField passwort;

    @FXML
    public Label registererror;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void registerNow() throws IOException {
        User user = new User(username.getText(), name.getText(),surname.getText(), mail.getText(), passwort.getText(), false);
        ObjectMapper mapper = new ObjectMapper();
        user.setRegisterFlag(true);
        String userstring = mapper.writeValueAsString(user);
        try {
            /*if(server.checkArrayList(user.getUserName())) {
                System.out.println("Username already in use!");
            }
            else {
                System.out.println("User registration successfull, you can now log in!");*/

                MyClient.printwriter.println(userstring);
                MyClient.printwriter.flush();
                



            //}

        } catch(Exception i) {
            throw new RuntimeException();
        }

        switchToSceneWithStage("/Login.fxml");
    }

    public void backToLogin(ActionEvent actionEvent) {
        switchToSceneWithStage("/Login.fxml");
    }
}