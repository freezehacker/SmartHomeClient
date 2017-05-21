package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by JK.
 */
public class InfoController {

    @FXML
    private Label info_content;

    private static final String INFO_CONTENT = "欢迎来到智能家居系统。\n"
            + "在这里你可以完成以下功能：\n"
            + "(1)门禁登记管理\n"
            + "(2)控制家居内的电器\n"
            + "(3)查看家居异常记录\n"
            + "希望你使用愉快！";

    @FXML
    public void initialize() {
        info_content.setText(INFO_CONTENT);
    }
}
