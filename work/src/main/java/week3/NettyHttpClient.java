package week3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;


import java.net.URI;
import java.net.URISyntaxException;


/**
 * netty实现httpClient
 *
 * @author : ocean_wll
 * @since 2021/5/16
 */
public class NettyHttpClient {

    public static void main(String[] args) {
        NettyHttpClient client = new NettyHttpClient();
        client.sendRequest("http://127.0.0.1:8003");
    }

    /**
     * 发起http请求
     *
     * @param url 请求的url路径
     */
    private void sendRequest(String url) {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            URI uri = new URI(url);
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new OceanChannelInitializer());

            // 获取连接
            Channel channel = bootstrap.connect(uri.getHost(), uri.getPort()).sync().channel();

            channel.closeFuture().sync();
        } catch (InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }
}

class OceanChannelInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * ChannelPipeline的常用方法
     * addFirst(...)   //添加ChannelHandler在ChannelPipeline的第一个位置
     * addBefore(...)   //在ChannelPipeline中指定的ChannelHandler名称之前添加ChannelHandler
     * addAfter(...)   //在ChannelPipeline中指定的ChannelHandler名称之后添加ChannelHandler
     * addLast(...)   //在ChannelPipeline的末尾添加ChannelHandler
     * remove(...)   //删除ChannelPipeline中指定的ChannelHandler
     * replace(...)   //替换ChannelPipeline中指定的ChannelHandler
     *
     * @param ch
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpResponseDecoder());
        pipeline.addLast(new HttpRequestEncoder());
        pipeline.addLast(new OceanHttpHandler());
    }
}

class OceanHttpHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpResponse) {
            try {
                HttpResponse response = (HttpResponse) msg;
                HttpHeaders headers = response.headers();
                for (String head : headers.names()) {
                    // 输出每个header
                    System.out.println(head);
                }
            } finally {
                ReferenceCountUtil.release(msg);
            }
        }
    }
}
