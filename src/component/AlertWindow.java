package component;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by JK.
 *
 * 自定义弹出框，相当于Android中的AlertDialog
 * 需要增加按钮点击事件时，可以增加一个回调函数作为参数
 */
public class AlertWindow {

    private String title;
    private String message;

    public AlertWindow(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public void show() {
        Stage window = new Stage();
        window.setTitle(title);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(300);
        window.setMinHeight(150);

        Button button = new Button("关闭");
        button.setOnAction(e -> window.close());

        Label label = new Label(message);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        //使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
        window.showAndWait();
    }
}
