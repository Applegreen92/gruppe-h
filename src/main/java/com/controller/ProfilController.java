package com.controller;

import com.model.MyClient;
import com.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfilController extends SceneController implements Initializable {

    @FXML
    public Text name_id, vorname_id, matr_id, email_id;

    public User user;

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("TestUser:"+MyClient.user.toString());
        name_id.setText(MyClient.user.getFamilyName());
        vorname_id.setText(MyClient.user.getGivenName());
        matr_id.setText(MyClient.user.getUserName());
        email_id.setText(MyClient.user.geteMail());


    }

    public void changemail(ActionEvent actionEvent) {
    }

   // public void addmanually(ActionEvent actionEvent) {
    //}

    public void autodownload(ActionEvent actionEvent) {
    }

    public void userwatchlist(ActionEvent actionEvent) {
    }
    public void addmanually(ActionEvent actionEvent) {switchToSceneWithStage("/addFilmGUI.fxml"); }
    // public void autodownload() { switchToSceneWithStage("/downloadwindown.fxml")}
    // public void changemail() { switchToSceneWithStage("/mailchange.fxml")}
    // public void userwatchlist () { switchToSceneWithStage("/userwatchlist.fxml")}
}
