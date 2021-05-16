package week3.problem2.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import week3.problem2.filter.HttpRequestFilter;
import week3.problem2.filter.HttpResponseFilter;
import week3.problem2.router.HttpRouter;

import java.util.List;

/**
 * @author : ocean_wll
 * @since 2021/5/16
 */
public class GatewayInitializer extends ChannelInitializer<SocketChannel> {

    private final List<String> endPointList;
    private final HttpRouter httpRouter;
    private final HttpRequestFilter requestFilter;
    private final HttpResponseFilter responseFilter;

    public GatewayInitializer(List<String> endPointList,
                              HttpRouter httpRouter,
                              HttpRequestFilter requestFilter,
                              HttpResponseFilter responseFilter) {
        this.endPointList = endPointList;
        this.httpRouter = httpRouter;
        this.requestFilter = requestFilter;
        this.responseFilter = responseFilter;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        pipeline.addLast(new GatewayHandler(endPointList, httpRouter, requestFilter, responseFilter));
    }
}
