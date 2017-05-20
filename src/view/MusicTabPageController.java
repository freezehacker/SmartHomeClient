package view;

import bean.RESPONSE.MusicResponse;
import bean.VO.Music;
import com.google.gson.Gson;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JK.
 */
public class MusicTabPageController {

    private final String MOCK_FILE = "src/mock/mock_music.json";

    @FXML
    private ListView<String> musicTextListView = new ListView<>();
    private ObservableList<String> musicObsList;
    @FXML
    private Label selectedMusic;

    //@FXML private ImageView pause;
    @FXML private Button previous;
    @FXML private Button next;
    @FXML private Button pause;


    @FXML
    public void initialize() {
        String res = StringUtils.fromFile(MOCK_FILE);
        MusicResponse resp = new Gson().fromJson(res, MusicResponse.class);
        List<String> musicTextList = new ArrayList<>();
        for (Music music : resp.getData()) {
            String musicText = new StringBuilder()
                    .append(music.getArtist())
                    .append(" - ")
                    .append(music.getName())
                    .toString();
            musicTextList.add(musicText);
        }

        musicObsList = FXCollections.observableList(musicTextList);
        musicTextListView.setItems(musicObsList);

        musicTextListView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
                    selectedMusic.setText(newValue);
                }
        );

        ImageView iv_pause = new ImageView(new Image(getClass().getResourceAsStream("pause_96px_1205832_easyicon.net.png")));
        iv_pause.setFitHeight(80);
        iv_pause.setFitWidth(80);
        pause.setGraphic(iv_pause);
        pause.setOnAction(e -> {
            System.out.println("hello world");
        });

        ImageView iv_previous = new ImageView(new Image(getClass().getResourceAsStream("previous_96px_1205837_easyicon.net.png")));
        iv_previous.setFitHeight(50);
        iv_previous.setFitWidth(50);
        previous.setGraphic(iv_previous);
        previous.setOnAction(e -> {
            System.out.println("hello world");
        });

        ImageView iv_next = new ImageView(new Image(getClass().getResourceAsStream("next_96px_1205820_easyicon.net.png")));
        iv_next.setFitHeight(50);
        iv_next.setFitWidth(50);
        next.setGraphic(iv_next);
        next.setOnAction(e -> {
            System.out.println("hello world");
        });
    }
}
