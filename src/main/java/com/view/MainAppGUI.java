package com.view;


import com.model.MyClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainAppGUI extends Application {


    public static Stage current;
    public static MyClient currentClient;

    public static Stage getCurr() {
        return current;
    }

    public static void setCurrClient(MyClient loginUser) {
        MainAppGUI.currentClient = loginUser;
    }

    public static MyClient getCurrClient() {
        return currentClient;
    }

    @Override
    public void start(Stage stage) throws IOException {
        current = stage;
        //Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        FXMLLoader loader = new FXMLLoader(MainAppGUI.class.getResource("Login.fxml"));
        Scene scene = new Scene(loader.load());
        current.setTitle("Login");
        current.setScene(scene);
        current.show();
    }

    public static void main() {

        launch();
    }
}