package view;

import com.google.common.eventbus.Subscribe;
import eventbus.ConsoleEvent;
import eventbus.EventBus;
import eventbus.RefreshListEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import utils.RFIDHelper;
import utils.StringUtils;
import utils.TimeUtils;

import java.awt.*;
import java.sql.Time;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by JK.
 * <p>
 * MainController负责Main界面的行为
 */
public class MainController {

    @FXML private Label curTime;

    @FXML private TextArea console;

    @FXML
    public void initialize() {
        //new Timer().schedule(new AccessTimeTask(), 0, 500);
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new AccessTimeTask(), 0, 500, TimeUnit.MILLISECONDS);

        EventBus.getDefault().register(this);
    }

    /* 改变UI一定要在主线程中进行 */
    @Subscribe()
    public void onEvent(ConsoleEvent consoleEvent) {
        if (!StringUtils.isEmpty(console.getText())) {
            console.setText(console.getText() + "\n"
                    + consoleEvent.getDt() + "  " + consoleEvent.getMsg());
        } else {
            console.setText(consoleEvent.getDt() + "  " + consoleEvent.getMsg());
        }
        console.setScrollTop(console.getHeight() + 10);
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
                    curTime.setText(TimeUtils.getCurrentTimeString());
                }
            });
        }
    }

    @FXML
    public void onSelectDoorCrudTab() {
        EventBus.getDefault().post(new RefreshListEvent());
    }
}
