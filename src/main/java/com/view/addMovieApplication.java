package com.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class addMovieApplication extends Application {

    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(addMovieApplication.class.getResource("addFilmGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Here you can manually put in movies");
        stage.setScene(scene);
        stage.show();


    }
    public static void main(String[] args) {launch(args);}
}
