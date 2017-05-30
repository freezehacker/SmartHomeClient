package utils;

import network.NetworkClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JK.
 *
 * 短信模块。以类(utils)的方式。
 */
public class MessageUtils {

    public static final String TEMPLATE_INDEX = "1765664";
    public static final String URL = "https://sms.yunpian.com/v2/sms/tpl_single_send.json";
    public static final String API_KEY = "fc1f9a7088d0feb703e320aaca16f65f";

    public static void sendTemplateMessage(String mobile, String message) {
        System.out.println("sendTemplateMessage方法");

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("apikey", API_KEY);
            params.put("mobile", mobile);
            params.put("tpl_id", TEMPLATE_INDEX);
            params.put("tpl_value",
                    URLEncoder.encode("#text#", "utf-8") + "=" + URLEncoder.encode(message, "utf-8"));

            NetworkClient.getInstance().syncPost(URL, params);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }
}
