package com.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class addMovieApplication extends Application {

    public void start(Stage stage) throws Exception {
        Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("addFilmGUI.fxml")));
        //FXMLLoader fxmlLoader = new FXMLLoader(addMovieApplication.class.getResource("addFilmGUI.fxml"));
        //Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Here you can manually put in movies");
        stage.setScene(newScene);
        stage.show();


    }
    public static void main() {launch();}
}
