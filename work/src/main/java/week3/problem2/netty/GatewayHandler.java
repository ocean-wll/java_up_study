package week3.problem2.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import week3.problem2.filter.HttpRequestFilter;
import week3.problem2.filter.HttpResponseFilter;
import week3.problem2.router.HttpRouter;

import java.io.IOException;
import java.util.List;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author : ocean_wll
 * @since 2021/5/16
 */
public class GatewayHandler extends ChannelInboundHandlerAdapter {

    private final List<String> endPointList;
    private final HttpRouter httpRouter;
    private final HttpRequestFilter requestFilter;
    private final HttpResponseFilter responseFilter;

    public GatewayHandler(List<String> endPointList,
                          HttpRouter httpRouter,
                          HttpRequestFilter requestFilter,
                          HttpResponseFilter responseFilter) {
        this.endPointList = endPointList;
        this.httpRouter = httpRouter;
        this.requestFilter = requestFilter;
        this.responseFilter = responseFilter;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            handle(fullRequest, ctx);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    /**
     * 处理请求
     *
     * @param request FullHttpRequest
     * @param context ChannelHandlerContext
     * @return body
     */
    private void handle(FullHttpRequest request, ChannelHandlerContext context) {

        // 路由获取请求地址
        String url = httpRouter.router(endPointList);

        // 经过RequestFilter
        requestFilter.filter(request);

        // 发起请求
        HttpResponse response = reqOtherServer(url);

        FullHttpResponse fullHttpResponse = null;
        try {
            byte[] body = EntityUtils.toByteArray(response.getEntity());
            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));

            fullHttpResponse.headers().set("Content-Type", "application/json");
            fullHttpResponse.headers().setInt("Content-Length",
                    Integer.parseInt(response.getFirstHeader("Content-Length").getValue()));

            // 经过responseFilter
            responseFilter.filter(fullHttpResponse);

        } catch (Exception e) {
            e.printStackTrace();
            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (request != null) {
                if (!HttpUtil.isKeepAlive(request)) {
                    context.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    context.write(fullHttpResponse);
                }
            }
            context.flush();
        }

    }

    /**
     * 请求第三方服务进行获取数据
     *
     * @return response的 body 值
     */
    private HttpResponse reqOtherServer(String url) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        try {
            return httpClient.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
