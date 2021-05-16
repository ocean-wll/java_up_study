package week3.problem2;

import week3.problem2.netty.NettyServer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ocean_wll
 * @since 2021/5/16
 */
public class ServerApplication {

    public static void main(String[] args) {
        List<String> endpointList = new ArrayList<>();
        endpointList.add("http://127.0.0.1:8010");
        endpointList.add("http://127.0.0.1:8020");

        NettyServer nettyServer = new NettyServer(endpointList);

        nettyServer.run();
    }
}
