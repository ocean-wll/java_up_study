import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : ocean_wll
 * @since 2021/5/9
 */
public class HttpServer01 {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8001);
            while (true) {
                Socket socket = serverSocket.accept();
                service(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void service(Socket socket) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello ocean_wll 01";

            // 不加 Content-Length 会导致浏览器无法显示数据
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
