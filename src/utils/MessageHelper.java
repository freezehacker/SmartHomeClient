package utils;

import bean.VO.ExceptionRecord;
import com.google.common.eventbus.Subscribe;
import constant.SysConfig;
import eventbus.EventBus;
import eventbus.ExceptionalEvent;
import network.NetworkClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JK.
 * <p>
 * 短信模块。
 * 以实例(helper)并且是单例的方式存在，便于事件总线管理。
 */
public class MessageHelper {

    private final String TEMPLATE_INDEX = "1765664";
    private final String URL = "https://sms.yunpian.com/v2/sms/tpl_single_send.json";
    private final String API_KEY = "fc1f9a7088d0feb703e320aaca16f65f";

    private static MessageHelper __instance;    // 单例

    static {
        __instance = new MessageHelper();
        EventBus.getDefault().register(__instance);
    }

    private MessageHelper() {
    }

    public static MessageHelper getInstance() {
        return __instance;
    }

    @Subscribe
    public void onEvent(ExceptionalEvent event) {
        String mobile = SysConfig.MOBILE;
        String message = event.getExceptionRecord().toString();
        this.send(mobile, message);
    }

    public void send(String mobile, String message) {
        //System.out.println("即将发送短信");
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("mobile", mobile);
            params.put("apikey", API_KEY);
            params.put("tpl_id", TEMPLATE_INDEX);
            params.put("tpl_value", URLEncoder.encode("#text#", "utf-8") + "=" + URLEncoder.encode(message, "utf-8"));
            NetworkClient.getInstance().syncPost(URL, params);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }
}
