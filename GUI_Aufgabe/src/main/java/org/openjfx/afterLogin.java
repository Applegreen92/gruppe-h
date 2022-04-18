package org.openjfx;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


public class afterLogin {
    @FXML
    private Button logout;

    public void userLogOut(ActionEvent event) throws IOException {
        MainApp m = new MainApp();
        m.changeScene("login.fxml");

    }
}


