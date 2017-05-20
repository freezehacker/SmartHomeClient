package bean.PO;

import java.util.Date;

/**
 * Created by JK.
 */
public class Device {

    private Integer dv_addr;
    private String dv_state;
    private String dv_position;
    private Hardware dv_hw;

    public Integer getDv_addr() {
        return dv_addr;
    }

    public void setDv_addr(Integer dv_addr) {
        this.dv_addr = dv_addr;
    }

    public String getDv_state() {
        return dv_state;
    }

    public void setDv_state(String dv_state) {
        this.dv_state = dv_state;
    }

    public String getDv_position() {
        return dv_position;
    }

    public void setDv_position(String dv_position) {
        this.dv_position = dv_position;
    }

    public Hardware getDv_hw() {
        return dv_hw;
    }

    public void setDv_hw(Hardware dv_hw) {
        this.dv_hw = dv_hw;
    }
}
