package week3.problem2.filter.impl;

import io.netty.handler.codec.http.HttpRequest;
import week3.problem2.filter.HttpRequestFilter;

/**
 * @author : ocean_wll
 * @since 2021/5/16
 */
public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(HttpRequest request) {
        // 增加header
        request.headers().set("author", "ocean");
    }
}
