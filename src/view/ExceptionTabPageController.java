package view;

import bean.VO.ExceptionRecord;
import com.google.common.eventbus.Subscribe;
import eventbus.EventBus;
import eventbus.ExceptionalEvent;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import repository.ExpRecordRepository;
import repository.impl.ExpRecordRepositoryImpl;
import utils.TimeUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by JK.
 */
public class ExceptionTabPageController {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    private ExpRecordRepository expRecordRepository = new ExpRecordRepositoryImpl();

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

    @FXML
    private DatePicker datepicker;

    private void refreshTable() {
        List<ExceptionRecord> records = expRecordRepository.queryByDay(TimeUtils.getCurrentDateString());
        ObservableList<ExceptionRecord> obsRecords = FXCollections.observableArrayList(records);
        exceptionRecordTable.setItems(obsRecords);
    }

    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);

        datepicker.setValue(LocalDate.now());   // 始终显示当前日期
        datepicker.setConverter(new StringConverter<LocalDate>() {
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
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

        usernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEr_username()));
        deviceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEr_device()));
        causeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEr_cause()));
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEr_date()));

        refreshTable();
    }

    /*选定某个日期*/
    @FXML
    public void datepicker_submit() {
        String date = datepicker.getValue().toString();
        //System.out.println(date);

        List<ExceptionRecord> records = expRecordRepository.queryByDay(date);
        ObservableList<ExceptionRecord> recordList = FXCollections.observableArrayList(records);
        exceptionRecordTable.setItems(recordList);
    }

    @FXML
    private void export() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("导出到...");
        fileChooser.setInitialFileName("异常记录.xls");
        File file = fileChooser.showSaveDialog(Main.getStage());
        if (file != null) { // 如果点击了保存，便会生成一个文件的内存映像
            List<ExceptionRecord> exceptionRecordList = expRecordRepository.list();

            try {
                FileOutputStream fos = new FileOutputStream(file);
                WritableWorkbook workbook = Workbook.createWorkbook(fos);
                WritableSheet sheet = workbook.createSheet("全部", 0);

                sheet.addCell(new Label(0, 0, "用户"));
                sheet.addCell(new Label(1, 0, "设备"));
                sheet.addCell(new Label(2, 0, "异常原因"));
                sheet.addCell(new Label(3, 0, "时间"));

                for (int i = 0, size = exceptionRecordList.size(); i < size; ++i) {
                    final ExceptionRecord er = exceptionRecordList.get(i);
                    sheet.addCell(new Label(0, i + 1, er.getEr_username()));
                    sheet.addCell(new Label(1, i + 1, er.getEr_device()));
                    sheet.addCell(new Label(2, i + 1, er.getEr_cause()));
                    sheet.addCell(new Label(3, i + 1, er.getEr_date()));
                }

                workbook.write();
                workbook.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* 删除那一天所有异常记录 */
    @FXML
    private void deleteAll() {
        String thatDay = datepicker.getValue().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        expRecordRepository.deleteByDay(thatDay);
        exceptionRecordTable.setItems(null);
    }

    @Subscribe
    public void onEvent(ExceptionalEvent exceptionalEvent) {
        ExceptionRecord record = exceptionalEvent.getExceptionRecord();
        expRecordRepository.insert(record);
        refreshTable();
    }


}
