package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by JK on 2017/4/13.
 */
public class SceneSwitcher {

    private static SceneSwitcher mInstance = null;
    private Stage stage = null;

    private SceneSwitcher(Stage stage) {
        this.stage = stage;
    }

    public static SceneSwitcher getInstance(Stage stage) {
        if (mInstance == null) {
            synchronized (SceneSwitcher.class) {
                if (mInstance == null) {
                    mInstance = new SceneSwitcher(stage);
                }
            }
        }
        return mInstance;
    }

    public void switchTo(String fxmlFilePath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Parent root = loader.load();
            Scene scene = new Scene(root);


            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
