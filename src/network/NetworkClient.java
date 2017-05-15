package network;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * Created by JK on 2017/4/13.
 */
public class NetworkClient {

    private static NetworkClient client = null;
    private OkHttpClient okHttpClient = null;

    // Hide the constructor.
    private NetworkClient() {
        okHttpClient = new OkHttpClient();
    }

    // Singleton.
    public static NetworkClient getInstance() {
        if (client == null) {
            synchronized (NetworkClient.class) {
                if (client == null) {
                    client = new NetworkClient();
                }
            }
        }
        return client;
    }

    /* Synchronous get */
    public String syncGet(final String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                return response.message();
            }
        } catch (IOException ioe) {
            return ioe.getMessage();
        }
    }

    /* Synchronous post */
    public String syncPost(final String url, Map<String, Object> params) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue().toString());
        }
        RequestBody requestBody = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)  // post some data to server
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();

            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                return response.message();
            }
        } catch (IOException ioe) {
            return ioe.getMessage();
        }
    }

    /* Asynchronous get */
    public void asyncGet(final String url, Callback cb) {

    }

    /* Asynchronous post */
    public void asyncPost(final String url, Callback cb) {

    }


    /* Asynchronous callback, which carries data. */
    public interface Callback {
        void onSuccess(String result);

        void onError(String error);
    }
}
