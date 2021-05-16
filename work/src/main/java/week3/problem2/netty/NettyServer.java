package week3.problem2.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import week3.problem2.filter.HttpRequestFilter;
import week3.problem2.filter.HttpResponseFilter;
import week3.problem2.filter.impl.HeaderHttpRequestFilter;
import week3.problem2.filter.impl.HeaderHttpResponseFilter;
import week3.problem2.router.HttpRouter;
import week3.problem2.router.impl.RandomHttpRouter;

import java.util.List;

/**
 * @author : ocean_wll
 * @since 2021/5/16
 */
public class NettyServer {

    private final List<String> endPointList;
    private final HttpRouter httpRouter;

    /**
     * TODO ocean_wll requestFilter 和 responseFilter 可以换成集合
     */
    private final HttpRequestFilter requestFilter;
    private final HttpResponseFilter responseFilter;

    public NettyServer(List<String> endPointList) {
        this.endPointList = endPointList;
        this.httpRouter = new RandomHttpRouter();
        this.requestFilter = new HeaderHttpRequestFilter();
        this.responseFilter = new HeaderHttpResponseFilter();
    }

    public void run() {
        int port = 8000;
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workGroup = new NioEventLoopGroup(32);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new GatewayInitializer(endPointList, httpRouter, requestFilter, responseFilter));

            Channel channel = bootstrap.bind(port).sync().channel();

            System.out.println("开启netty服务，监听端口为：" + port);
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
