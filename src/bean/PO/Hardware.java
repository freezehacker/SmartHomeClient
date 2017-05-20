package bean.PO;

import java.util.Date;

/**
 * Created by JK.
 */
public class Hardware {

    private Integer hw_no;
    private String hw_type;
    private String hw_name;
    private String hw_madein;
    private String hw_manufacturer;
    private Date hw_date;
    private String hw_part_number;

    public Integer getHw_no() {
        return hw_no;
    }

    public void setHw_no(Integer hw_no) {
        this.hw_no = hw_no;
    }

    public String getHw_type() {
        return hw_type;
    }

    public void setHw_type(String hw_type) {
        this.hw_type = hw_type;
    }

    public String getHw_name() {
        return hw_name;
    }

    public void setHw_name(String hw_name) {
        this.hw_name = hw_name;
    }

    public String getHw_madein() {
        return hw_madein;
    }

    public void setHw_madein(String hw_madein) {
        this.hw_madein = hw_madein;
    }

    public String getHw_manufacturer() {
        return hw_manufacturer;
    }

    public void setHw_manufacturer(String hw_manufacturer) {
        this.hw_manufacturer = hw_manufacturer;
    }

    public Date getHw_date() {
        return hw_date;
    }

    public void setHw_date(Date hw_date) {
        this.hw_date = hw_date;
    }

    public String getHw_part_number() {
        return hw_part_number;
    }

    public void setHw_part_number(String hw_part_number) {
        this.hw_part_number = hw_part_number;
    }
}
