package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    private static Stage curr;
    private static User currUser;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        curr = stage;
        curr.setScene(new Scene(root));
        curr.setTitle("Login");
        curr.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public static User getCurrUser() {
        return currUser;
    }

    public static void setCurrUser(User currUser) {
        MainApp.currUser = currUser;
    }

    public static Stage getCurr() {
        return curr;
    }


}