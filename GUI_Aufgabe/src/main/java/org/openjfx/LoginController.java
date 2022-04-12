package org.openjfx;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


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

    // Wird ausgeführt wenn der login Button gedrückt wird
    public void loginPressed() throws IOException {



    }

    //Wird ausgeführt wenn der register Button gedrückt wird
    public void registerPressed() {
        switchToSceneWithStage("/fxml/RegisterStudent.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
