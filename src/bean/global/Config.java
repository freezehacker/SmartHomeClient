package bean.global;

/**
 * Created by JK.
 */
public class Config {

    public static final String APP_NAME = "智能家居客户端";    // 系统名称
    public static int SYNC_READ_PERIOD = 5000;  // 同步读间隔
    public static final int SYNC_TIME_PERIOD = 800; // 时间同步间隔
    public static HomeState HOME_STATE = HomeState.NORMAL;  // 默认模式

    public enum HomeState {
        NORMAL, // 正常模式
        OFF,    // 关闭模式
        SMART   // 智能（自动）模式
    }
}
