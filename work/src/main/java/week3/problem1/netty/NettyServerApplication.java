package week3.problem1.netty;

/**
 * @author ocean_wll
 * @date 2021/5/13
 */
public class NettyServerApplication {

    public static void main(String[] args) {
        HttpServer server = new HttpServer(false, 9001);
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
