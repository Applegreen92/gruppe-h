package com.controller;

import com.testPackage.stuffToDelete.MainAppGUI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfilController extends SceneController implements Initializable {
    @FXML
     public Text name_id, vorname_id, username_id, email_id;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name_id.setText(MainAppGUI.getCurrClient().getUser().getFamilyName());
        vorname_id.setText(MainAppGUI.getCurrClient().getUser().getGivenName());
        username_id.setText(MainAppGUI.getCurrClient().getUser().getUserName());
        email_id.setText(MainAppGUI.getCurrClient().getUser().geteMail());


    }
}
