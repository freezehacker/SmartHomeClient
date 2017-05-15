package view;

import bean.RESPONSE.AirConditionerResponse;
import bean.RESPONSE.Response;
import bean.VO.AirConditioner;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import network.NetworkClient;
import utils.StringUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JK on 2017/4/20.
 */
public class AirconditioningTabPageController {

    public static long SYNC_READ_PERIOD = 5000;

    public static final String MOCK_SERVER = "http://www.mockhttp.cn/mock/mock/accessSensor";
    public static final String MOCK_FILE = "src/mock/mock.json";

    @FXML private Label air1_id;
    @FXML private Label air1_location;
    @FXML private Slider air1_open;
    @FXML private Slider air1_windTemp;
    @FXML private Slider air1_windSpeed;
    @FXML private Slider air1_windMode;

    @FXML private Label air2_id;
    @FXML private Label air2_location;
    @FXML private Slider air2_open;
    @FXML private Slider air2_windTemp;
    @FXML private Slider air2_windSpeed;
    @FXML private Slider air2_windMode;

    @FXML
    public void initialize() {

       new Timer().schedule(new SyncReading(), SYNC_READ_PERIOD, SYNC_READ_PERIOD);

    }


    /*定时读取家电状态信息*/
    class SyncReading extends TimerTask {
        @Override
        public void run() {
            //String res = NetworkClient.getInstance().syncGet(MOCK_SERVER);
            //System.out.println(res);
            String res = StringUtils.fromFile(MOCK_FILE);
            System.out.println(res);
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

                print("同步读成功");
            } else {

                print("读取失败");
            }
        }
    }

    private void print(String str) {
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        mainController = loader.<MainController>getController();
        mainController.receive(str);
        */

        MainController.globalAddMessage(str);

        //System.out.println(str);
    }
}
