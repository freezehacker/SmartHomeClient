package view;

import Component.AlertWindow;
import bean.PO.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import sun.swing.StringUIClientPropertyKey;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JK.
 */
public class DoorTabPageController {

    private UserRepository userRepository = new UserRepositoryImpl();

    private ToggleGroup toggleGroup;
    @FXML
    private Button submit;
    @FXML
    private RadioButton userMale;
    @FXML
    private RadioButton userFemale;
    @FXML
    private TextField userId;
    @FXML
    private TextField userName;
    @FXML
    private TextField userPhone;

    @FXML
    public void initialize() {
        toggleGroup = new ToggleGroup();
        userMale.setToggleGroup(toggleGroup);
        userFemale.setToggleGroup(toggleGroup);
        userMale.setSelected(true);

        userPhone.addEventFilter(KeyEvent.KEY_TYPED, new NumberOnlyHandler());
        userId.addEventFilter(KeyEvent.KEY_TYPED, new NumberOnlyHandler());
    }

    @FXML
    private void submitUser(ActionEvent event) {
        if (StringUtils.isEmpty(userId.getText())) {
            new AlertWindow("错误", "请输入标签ID！").show();
            return;
        } else {
            if (!userId.getText().matches("\\d{9}")) {
                new AlertWindow("错误", "请输入格式正确的标签ID（9位数字）！").show();
                return;
            }
        }

        if (StringUtils.isEmpty(userName.getText())) {
            new AlertWindow("错误", "请输入姓名！").show();
            return;
        }
        /*if (StringUtils.isEmpty(userPhone.getText())) {
            new AlertWindow("错误", "电话号码不能为空").show();
            return;
        }*/
        if (!StringUtils.isEmpty(userPhone.getText())) {
            if (!userPhone.getText().matches("1[3458]\\d{9}")) {
                new AlertWindow("错误", "请输入格式正确的手机号！").show();
                return;
            }
        }


        String idString = userId.getText().trim();
        if (userRepository.exist(Integer.valueOf(idString))) {
            new AlertWindow("错误", "ID\"" + idString + "\"已经被使用，请制作新的RFID标签！").show();
            return;
        }

        String gender = userMale.isSelected() ? "M" : "F";
        userRepository.insert(new User(Integer.valueOf(idString), userName.getText(), gender, userPhone.getText()));
        new AlertWindow("提示", "户主身份登记成功").show();
    }

    /*只能输入数字*/
    class NumberOnlyHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            String ch = event.getCharacter();
            if (!ch.matches("[0-9]")) {
                event.consume();
            }
        }
    }
}
