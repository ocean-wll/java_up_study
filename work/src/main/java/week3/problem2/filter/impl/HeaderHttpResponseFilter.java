package week3.problem2.filter.impl;

import io.netty.handler.codec.http.HttpResponse;
import week3.problem2.filter.HttpResponseFilter;

/**
 * @author : ocean_wll
 * @since 2021/5/16
 */
public class HeaderHttpResponseFilter implements HttpResponseFilter {

    @Override
    public void filter(HttpResponse response) {
        response.headers().set("date", "2021-05-16");
    }
}
