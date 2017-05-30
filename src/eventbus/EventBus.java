package eventbus;

/**
 * Created by JK.
 * <p>
 * 设置成单例模式
 */
public class EventBus {

    private static com.google.common.eventbus.EventBus __instance = new com.google.common.eventbus.EventBus("JK");

    public EventBus() {
    }

    public static com.google.common.eventbus.EventBus getDefault() {
        return __instance;
    }
}
