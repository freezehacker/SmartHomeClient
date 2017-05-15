package view;

import Component.AlertWindow;
import bean.VO.ExceptionRecord;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;
import repository.ExpRecordRepository;
import repository.impl.ExpRecordRepositoryImpl;
import utils.MessageUtils;
import utils.TimeUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by JK on 2017/4/20.
 */
public class ExceptionTabPageController {

    private ExpRecordRepository expRecordRepository=new ExpRecordRepositoryImpl();

    private List<ExceptionRecord> records;
    private ObservableList<ExceptionRecord> exceptionRecords = FXCollections.observableArrayList();

    @FXML
    private TableView<ExceptionRecord> exceptionRecordTable;
    @FXML
    private TableColumn<ExceptionRecord, String> usernameColumn;
    @FXML
    private TableColumn<ExceptionRecord, String> deviceColumn;
    @FXML
    private TableColumn<ExceptionRecord, String> causeColumn;
    @FXML
    private TableColumn<ExceptionRecord, String> dateColumn;

    @FXML private DatePicker datepicker;

    @FXML
    public void initialize() {
        /*exceptionRecords.add(new ExceptionRecord("张三", "空调","温度超过了40摄氏度","2016-02-01 10:20"));
        exceptionRecords.add(new ExceptionRecord("张三", "温度传感器","室内温度高于安全值","2016-06-03 21:20"));
        exceptionRecords.add(new ExceptionRecord("张三", "温度传感器","室内温度高于安全值","2016-12-01 21:00"));
        exceptionRecords.add(new ExceptionRecord("李四", "温度传感器","室内温度高于安全值","2016-12-12 20:07"));
        exceptionRecords.add(new ExceptionRecord("张三", "湿度传感器","室内湿度值过低","2017-03-12 14:07"));*/

        //exceptionRecords.add(new ExceptionRecord("未知用户（111111111）", "-","有非户主进行刷卡操作","2017-5-7 15:18"));

        datepicker.setValue(LocalDate.now());   // 始终显示当前日期
        datepicker.setConverter(new StringConverter<LocalDate>() {

            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate object) {
                if (object == null) return "";
                return formatter.format(object);
            }

            @Override
            public LocalDate fromString(String string) {
                if (string == null || string.length() == 0) return null;
                return LocalDate.parse(string, formatter);
            }
        });

        String curDate = TimeUtils.getCurrentDateString();
        records = expRecordRepository.queryByDay(curDate);
        for (ExceptionRecord er:records) {
            exceptionRecords.add(er);
        }

        usernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEr_username()));
        deviceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEr_device()));
        causeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEr_cause()));
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEr_date()));

        exceptionRecordTable.setItems(exceptionRecords);
    }

    /*选定某个日期*/
    @FXML
    public void datepicker_submit() {
        String date = datepicker.getValue().toString();
        //System.out.println(date);
        records = expRecordRepository.queryByDay(date);
        exceptionRecords.clear();
        for (ExceptionRecord er:records) {
            exceptionRecords.add(er);
        }
        exceptionRecordTable.refresh();
    }

    @FXML
    private void export() {
        new AlertWindow("提示", "该功能暂未实现").show();

        MessageUtils.sendTemplateMessage("18819481197", "有非户主进行刷卡操作");

        MessageUtils.sendTemplateMessage("18819481197", "客厅温度（来自编号12345的温度传感器）超过预设值（35℃）");
    }

    @FXML
    private void deleteAll() {
        exceptionRecords.clear();

        expRecordRepository.deleteByDay(TimeUtils.getCurrentDateString());

        exceptionRecordTable.refresh();
    }
/*
    private void listData(List<ExceptionRecord> ers) {
        records.clear();
        records.addAll(ers);
        exceptionRecordTable.refresh();
    }

    private void addData(List<ExceptionRecord> ers) {
        records.addAll(ers);
        exceptionRecordTable.refresh();
    }*/
}
