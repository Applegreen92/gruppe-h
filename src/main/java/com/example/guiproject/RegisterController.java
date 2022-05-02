package com.example.guiproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class RegisterController extends SceneController implements Initializable {
    @FXML
    TextField vorname, nachname, matrnr, email;

    @FXML
    PasswordField passwort;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void registerNow() throws IOException {
        int tempMatr = Integer.parseInt(matrnr.getText());
        User user = new User(vorname.getText(), nachname.getText(), email.getText(), passwort.getText(), tempMatr);
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

        switchToSceneWithStage("Login.fxml");
    }
}