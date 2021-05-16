package week2;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * OkHttpClient 使用demo
 *
 * @author ocean_wll
 * @date 2021/5/13
 */
public class OkHttpClientDemo {

    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://127.0.0.1:9001/test").build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println(response.body().string());
            } else {
                System.out.println("请求失败！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
