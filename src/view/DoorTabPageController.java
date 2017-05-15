package view;

import Component.AlertWindow;
import bean.PO.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import sun.swing.StringUIClientPropertyKey;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JK on 2017/4/20.
 */
public class DoorTabPageController {

    private UserRepository userRepository = new UserRepositoryImpl();

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
    private void submitUser(ActionEvent event) {
        if (StringUtils.isEmpty(userId.getText())) {
            new AlertWindow("错误", "ID不能为空").show();
            return;
        }
        if (StringUtils.isEmpty(userName.getText())) {
            new AlertWindow("错误", "姓名不能为空").show();
            return;
        }
        if (StringUtils.isEmpty(userPhone.getText())) {
            new AlertWindow("错误", "电话号码不能为空").show();
            return;
        }


        String idString = userId.getText().trim();
        if (userRepository.exist(Integer.valueOf(idString))) {
            new AlertWindow("错误", "ID\"" + idString + "\"已经被使用，请制作新的RFID标签！").show();
            return;
        }

        userRepository.insert(new User(Integer.valueOf(idString), userName.getText(), "M", userPhone.getText()));
        new AlertWindow("提示", "户主身份登记成功").show();
    }
}
