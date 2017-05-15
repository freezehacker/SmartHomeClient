package bean.VO;

/**
 * Created by JK.
 */
public class AirConditioner {

    private String id;
    private String location;
    private int open;   // 0,1
    private double windTemp;
    private int windSpeed;  // 1~10
    private int windMode;   // 0,1,2

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public double getWindTemp() {
        return windTemp;
    }

    public void setWindTemp(double windTemp) {
        this.windTemp = windTemp;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindMode() {
        return windMode;
    }

    public void setWindMode(int windMode) {
        this.windMode = windMode;
    }
}
