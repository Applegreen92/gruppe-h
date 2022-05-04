package com.controller;


import com.model.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.JSONObject;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends SceneController implements Initializable {
    @FXML
    public TextField usernameField;

    @FXML
    public PasswordField passwordField;

<<<<<<< HEAD
   // Client client = new Client(); // Line von Rapha hinzugefügt
||||||| GUI
=======


>>>>>>> main
  /*  public void loginPressed() throws IOException {
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
*/


    public void registerPressed() {
        switchToSceneWithStage("/Register.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void loginPressed(ActionEvent actionEvent) throws IOException {
//        client.login(usernameField.getText(),passwordField.getText()); // Line von Rapha hinzugefügt
    }
}