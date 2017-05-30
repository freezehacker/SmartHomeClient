package eventbus;

/**
 * Created by JK.
 */
public class OpenDoorEvent {

    private String date;
    private String id;

    public OpenDoorEvent() {
    }

    public OpenDoorEvent(String date, String id) {
        this.date = date;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
