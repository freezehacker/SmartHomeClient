package view;

import bean.PO.User;
import com.google.common.eventbus.Subscribe;
import eventbus.EventBus;
import eventbus.RefreshListEvent;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;

import java.util.List;

/**
 * Created by JK.
 */
public class DoorTabCrudPageController {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> nameCol;
    @FXML
    private TableColumn<User, String> mobileCol;
    @FXML
    private TableColumn<User, String> genderCol;
    @FXML
    private TableColumn<User, String> idCol;

    private UserRepository userRepository = new UserRepositoryImpl();


    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);

        // 设置表格每一列的呈现形式
        nameCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getU_name()));
        idCol.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getU_id())));
        mobileCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getU_phone()));
        genderCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getU_gender()));

        refreshList();
    }

    @Subscribe
    public void onRefresh(RefreshListEvent event) {
        refreshList();
    }

    /* 重新读取并显示数据库中的用户 */
    public void refreshList() {
        // 取得数据库中的资料
        List<User> users = userRepository.list();

        // 为整个表格设置数据源
        ObservableList<User> obsList = FXCollections.observableArrayList(users);
        userTable.setItems(obsList);
    }

    @FXML
    public void deleteCurUser() {
        User user = userTable.getSelectionModel().getSelectedItem();
        userRepository.delete(user.getU_id());

        // 重新同步读取数据库
        refreshList();
    }
}
