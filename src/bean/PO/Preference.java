package bean.PO;

import java.util.Date;

/**
 * Created by JK on 2017/4/20.
 */
public class Preference {

    private Integer pref_id;
    private User pref_u;
    private Device pref_dv;
    private Date pref_date;

    public Integer getPref_id() {
        return pref_id;
    }

    public void setPref_id(Integer pref_id) {
        this.pref_id = pref_id;
    }

    public User getPref_u() {
        return pref_u;
    }

    public void setPref_u(User pref_u) {
        this.pref_u = pref_u;
    }

    public Device getPref_dv() {
        return pref_dv;
    }

    public void setPref_dv(Device pref_dv) {
        this.pref_dv = pref_dv;
    }

    public Date getPref_date() {
        return pref_date;
    }

    public void setPref_date(Date pref_date) {
        this.pref_date = pref_date;
    }
}
