package bean.PO;

/**
 * Created by JK.
 */
public class User {

    private Integer u_id;
    private String u_name;
    private String u_gender;
    private String u_phone;

    public User() {

    }

    public User(Integer u_id, String u_name, String u_gender, String u_phone) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.u_gender = u_gender;
        this.u_phone = u_phone;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_gender() {
        return u_gender;
    }

    public void setU_gender(String u_gender) {
        this.u_gender = u_gender;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }
}
