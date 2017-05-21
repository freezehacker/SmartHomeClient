package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import utils.TimeUtils;

import java.sql.Time;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JK.
 * <p>
 * MainController负责Main界面的行为
 */
public class MainController implements IReceiver {

    //@FXML private Label curTime;

    @FXML private TextArea console;

    private static TextArea globalConsole;

    // DEBUG
    public static void globalAddMessage(String msg) {
        String str = globalConsole.getText() + "\n"
                + msg + "(" + TimeUtils.getCurrentTimeString() + ")";
        globalConsole.setText(str);
        globalConsole.setScrollTop(globalConsole.getHeight());
    }

    @FXML
    public void initialize() {
        //new Timer().schedule(new AccessTimeTask(), 0, 500);
        globalConsole = console;
    }

    @FXML
    public void exitApp() {
        System.exit(0);
    }

    class AccessTimeTask extends TimerTask {
        @Override
        public void run() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //curTime.setText(TimeUtils.getCurrentDateTimeString());
                }
            });
        }
    }

    @Override
    public void receive(String message) {
        addMessage(message);
    }

    /* 给控制台增加一行log信息 */
    private void addMessage(String message) {
        String str = console.getText() + "\n"
                + message + "(" + TimeUtils.getCurrentTimeString() + ")";
        console.setText(str);
    }

    @FXML
    public void onSelectDoorCrudTab() {
        DoorTabCrudPageController.getInstance().refreshList();
    }
}
