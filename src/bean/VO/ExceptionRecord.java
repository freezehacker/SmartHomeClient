package bean.VO;

import utils.StringUtils;

/**
 * Created by JK.
 */
public class ExceptionRecord {

    private String er_username;
    private String er_device;
    private String er_cause;
    private String er_date;

    public ExceptionRecord() {
    }

    public ExceptionRecord(String er_username, String er_device, String er_cause, String er_date) {
        this.er_username = er_username;
        this.er_device = er_device;
        this.er_cause = er_cause;
        this.er_date = er_date;
    }

    public String getEr_username() {
        return er_username;
    }

    public void setEr_username(String er_username) {
        this.er_username = er_username;
    }

    public String getEr_device() {
        return er_device;
    }

    public void setEr_device(String er_device) {
        this.er_device = er_device;
    }

    public String getEr_cause() {
        return er_cause;
    }

    public void setEr_cause(String er_cause) {
        this.er_cause = er_cause;
    }

    public String getEr_date() {
        return er_date;
    }

    public void setEr_date(String er_date) {
        this.er_date = er_date;
    }

    @Override
    public String toString() {
        return (StringUtils.isEmpty(er_username) ? "" : "用户：" + er_username + ";")
                + (StringUtils.isEmpty(er_device) ? "" : "设备：" + er_device + ";")
                + "异常事件：" + er_cause + ";"
                + "时间：" + er_date;
    }
}
