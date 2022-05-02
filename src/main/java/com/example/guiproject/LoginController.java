package com.example.guiproject;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class LoginController extends SceneController implements Initializable {
    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    public void loginPressed() throws IOException {
        int matr = Integer.parseInt(usernameField.getText());
        String password = passwordField.getText();
        File f = new File(String.valueOf(Paths.get(matr + ".json")));
        if (f.exists() && !f.isDirectory()) {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(matr + ".json"));
            User loginUser = gson.fromJson(reader, User.class);
            System.out.println("Login");
            if (loginUser.getPasswort().equals(password)) {
                MainAppGUI.setCurrUser(loginUser);
                switchToSceneWithStage("Profil.fxml");
            } else {
                System.out.println("Falsches Passwort");
                usernameField.clear();
                passwordField.clear();
            }

        } else {
            System.out.println("Nutzer exsitiert nicht");
            usernameField.clear();
            passwordField.clear();
        }


    }

    public void registerPressed() {
        switchToSceneWithStage("Register.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}