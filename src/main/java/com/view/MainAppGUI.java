package com.view;


import com.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;




public class MainAppGUI extends Application {

    public static Stage current;
    public static User currentUser;
    public static Stage getCurr() {
        return current;
    }

    public static void setCurrUser(User loginUser) {
        MainAppGUI.currentUser = loginUser;
    }

    public static User getCurrUser() { return currentUser;
    }

    @Override
    public void start(Stage stage) throws IOException {
        current = stage;
        Parent root =  FXMLLoader.load(getClass().getResource("/Login.fxml"));
       // FXMLLoader loader = new FXMLLoader(MainAppGUI.class.getResource("Login.fxml"));
        Scene scene = new Scene(root);
        current.setTitle("Login");
        current.setScene(scene);
        current.show();
    }

    public static void main(String[] args) {
      // ummuenzen auf server
       // ServerTest server = new ServerTest();
       // server.startListening();
        launch(args);
    }
}