package week3.problem2.filter;


import io.netty.handler.codec.http.HttpResponse;

/**
 * @author : ocean_wll
 * @since 2021/5/16
 */
public interface HttpResponseFilter {

    /**
     * 过滤
     *
     * @param response HttpResponse对象
     */
    void filter(HttpResponse response);
}
