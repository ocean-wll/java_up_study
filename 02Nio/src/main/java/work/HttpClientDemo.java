package work;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 利用httpClient发起http请求
 *
 * @author ocean_wll
 * @date 2021/5/13
 */
public class HttpClientDemo {

    public static void main(String[] args) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://127.0.0.1:9001/test");
        try {
            HttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity);
            System.out.println(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}