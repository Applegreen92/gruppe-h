package com.example.guiproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfilController extends SceneController implements Initializable {
    @FXML
    Text name_id, vorname_id, matr_id, email_id;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name_id.setText(MainAppGUI.getCurrUser().getNachname());
        vorname_id.setText(MainAppGUI.getCurrUser().getVorname());
        matr_id.setText(Integer.toString(MainAppGUI.getCurrUser().getMatrNr()));
        email_id.setText(MainAppGUI.getCurrUser().getEmail());


    }
}
