package eventbus;

/**
 * Created by JK.
 */
public class ConsoleEvent {

    private String msg;
    private String dt;

    public ConsoleEvent() {
    }

    public ConsoleEvent(String msg, String dt) {
        this.msg = msg;
        this.dt = dt;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
}
