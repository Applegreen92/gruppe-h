package com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;
import com.controller.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController extends SceneController implements Initializable {
    @FXML
    public TextField username, name, surname, mail;

    @FXML
    public PasswordField passwort;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void registerNow() throws IOException {
        User user = new User(username.getText(), name.getText(),surname.getText(), mail.getText(), passwort.getText(), false);
        ObjectMapper mapper = new ObjectMapper();
        String userstring = mapper.writeValueAsString(user);
        try {
            Socket socket = new Socket("localhost", 7779);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            pw.println(userstring);
            pw.flush();
        } catch(Exception i) {
            throw new RuntimeException();
        }

        switchToSceneWithStage("/Login.fxml");
    }

    public void backToLogin(ActionEvent actionEvent) {
        switchToSceneWithStage("/Login.fxml");
    }
}