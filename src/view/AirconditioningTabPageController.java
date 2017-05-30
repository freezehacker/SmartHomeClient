package view;

import bean.RESPONSE.AirConditionerResponse;
import bean.RESPONSE.Response;
import bean.VO.AirConditioner;
import com.google.gson.Gson;
import eventbus.ConsoleEvent;
import eventbus.EventBus;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import network.NetworkClient;
import utils.StringUtils;
import utils.TimeUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by JK.
 */
public class AirconditioningTabPageController {

    public static long SYNC_READ_PERIOD = 5000;

    public static final String MOCK_FILE = "src/mock/mock.json";

    @FXML
    private Label air1_id;
    @FXML
    private Label air1_location;
    @FXML
    private Slider air1_open;
    @FXML
    private Slider air1_windTemp;
    @FXML
    private Slider air1_windSpeed;
    @FXML
    private Slider air1_windMode;

    @FXML
    private Label air2_id;
    @FXML
    private Label air2_location;
    @FXML
    private Slider air2_open;
    @FXML
    private Slider air2_windTemp;
    @FXML
    private Slider air2_windSpeed;
    @FXML
    private Slider air2_windMode;

    @FXML
    public void initialize() {
        //new Timer().schedule(new SyncReading(), SYNC_READ_PERIOD, SYNC_READ_PERIOD);
        ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
        pool.scheduleAtFixedRate(new SyncReading(), 3, 5, TimeUnit.SECONDS);
    }


    /*定时读取家电状态信息*/
    class SyncReading extends TimerTask {
        @Override
        public void run() {
            String res = StringUtils.fromFile(MOCK_FILE);
            //System.out.println(res);
            AirConditionerResponse airResp = new Gson().fromJson(res, AirConditionerResponse.class);
            if ("200".equals(airResp.getCode())) {
                //  更新UI
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        List<AirConditioner> airs = airResp.getData();

                        air1_id.setText("编号：" + airs.get(0).getId());
                        air1_location.setText("位置：" + airs.get(0).getLocation());
                        air1_open.setValue(airs.get(0).getOpen());
                        air1_windTemp.setValue(airs.get(0).getWindTemp());
                        air1_windSpeed.setValue(airs.get(0).getWindSpeed());
                        air1_windMode.setValue(airs.get(0).getWindMode());

                        air2_id.setText("编号：" + airs.get(1).getId());
                        air2_location.setText("位置：" + airs.get(1).getLocation());
                        air2_open.setValue(airs.get(1).getOpen());
                        air2_windTemp.setValue(airs.get(1).getWindTemp());
                        air2_windSpeed.setValue(airs.get(1).getWindSpeed());
                        air2_windMode.setValue(airs.get(1).getWindMode());

                    }
                });

                //EventBus.getDefault().post(new ConsoleEvent("同步成功", TimeUtils.getCurrentTimeString()));
            } else {
                EventBus.getDefault().post(new ConsoleEvent("同步失败", TimeUtils.getCurrentTimeString()));
            }
        }
    }
}
