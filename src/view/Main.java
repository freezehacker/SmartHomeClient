package view;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Main只负责加载整个应用
 */
public class Main extends Application {

    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 600;

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        //primaryStage.setTitle("Smart Home");
        primaryStage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));

        stage = primaryStage;

        primaryStage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
