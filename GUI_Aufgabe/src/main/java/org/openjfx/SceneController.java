package org.openjfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public abstract class SceneController {

    //Wechselt zur gewünschten Scene die mit "/ordnername/dateiname.fxml" angesprochen wird
    public void switchToScene(String fxmlPath){
        try {
            Scene newScene = FXMLLoader.load(getClass().getResource(fxmlPath));
            MainApp.getCurr().setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Wechselt zur gewünschten Scene, falls diese Scene eine Stage haben sollte. (Funktioniert auch wie oben beschrieben)
    public void switchToSceneWithStage(String fxmlPath){
        try {
            Scene newScene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));
            MainApp.getCurr().setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
