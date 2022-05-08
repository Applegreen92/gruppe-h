package com.controller;



import com.Main;
import com.model.MyClient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public abstract class SceneController {


    public void switchToScene(String fxmlPath){
        try {
            Scene newScene = FXMLLoader.load(getClass().getResource(fxmlPath));
           MyClient.getCurr().setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void switchToSceneWithStage(String fxmlPath){
        try {
            Scene newScene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));
            MyClient.getCurr().setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}