package com.controller;

import com.view.MainAppGUI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfilController extends SceneController implements Initializable {
    @FXML
    Text name_id, vorname_id, username_id, email_id;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name_id.setText(MainAppGUI.getCurrUser().getFamilyName());
        vorname_id.setText(MainAppGUI.getCurrUser().getGivenName());
        username_id.setText(MainAppGUI.getCurrUser().getUserName());
        email_id.setText(MainAppGUI.getCurrUser().geteMail());


    }
}
