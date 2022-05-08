package com.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.MyClient;
import com.model.User;
import com.model.myServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.JSONObject;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoginController extends SceneController implements Initializable {
    @FXML
    public TextField usernameField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public Label errorlabel;

   // Client client = new Client(); // Line von Rapha hinzugefügt








    public void registerPressed() {
        switchToSceneWithStage("/Register.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void loginPressed(ActionEvent actionEvent) throws IOException {
//        client.login(usernameField.getText(),passwordField.getText()); // Line von Rapha hinzugefügt

      /*  if (myServer.getUser(usernameField.getText(), passwordField.getText()) != null) {
            errorlabel.setText("Login Erfolgreich");
            client.setUser(myServer.getUser(usernameField.getText(), passwordField.getText()));
            switchToSceneWithStage("/Profil.fxml");
        } else {
            errorlabel.setText("Falsche eMail oder passwort!");
        }

       */

        User user = new User("","","",usernameField.getText(),passwordField.getText(),false);
        ObjectMapper mapper = new ObjectMapper();
        user.setLoginFlag(true);
        String userstring = mapper.writeValueAsString(user);
        try {
            MyClient.printwriter.println(userstring);
            MyClient.printwriter.flush();
            String UserString = MyClient.scanner.nextLine();
            System.out.println(UserString);
            User newUser = mapper.readValue(UserString, User.class);
            MyClient.user = newUser;
            switchToSceneWithStage("/Profil.fxml");
        }
        catch (Exception i) {
            throw new RuntimeException();
        }

    }
    }
