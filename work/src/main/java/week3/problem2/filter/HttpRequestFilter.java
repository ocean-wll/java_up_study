package week3.problem2.filter;

import io.netty.handler.codec.http.HttpRequest;

/**
 * @author : ocean_wll
 * @since 2021/5/16
 */
public interface HttpRequestFilter {

    /**
     * 过滤
     *
     * @param request httpRequest对象
     */
    void filter(HttpRequest request);
}
