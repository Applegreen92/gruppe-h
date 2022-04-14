package org.openjfx;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class LoginController extends SceneController implements Initializable {
    @FXML
    TextField matrikelnr;

    @FXML
    PasswordField passwort;

    @FXML
    private Button loginbutton;

    @FXML
    private Button registerbutton;

    @FXML
    private Label wronglogin;





    // Wird ausgeführt wenn der login Button gedrückt wird
    public void loginPressed() throws IOException {

    }

    //Wird bei login ausgeführt
    public void userlogin(javafx.event.ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        MainApp m = new MainApp();
        if (matrikelnr.getText().toString().equals("Lorenz") && passwort.getText().toString().equals("testpw")) {
            wronglogin.setText("Erfolgreicher Login!");

            m.changeScene("afterlogin.fxml");
        }
        else if (matrikelnr.getText().isEmpty() && passwort.getText().isEmpty()) {
            wronglogin.setText("Bitte Matrikelnummer und Passwort eingeben!");
        } else {
            wronglogin.setText("Falsche Matrikelnummer oder Passwort");
        }
    }

    //Wird ausgeführt wenn der register Button gedrückt wird
    public void registerPressed() {
        switchToSceneWithStage("/fxml/RegisterStudent.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
