package netty1.chapter2.one;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 时间服务器：同步阻塞式IO
 * 每当有一个新的客户端请求接入时，服务端必须创建一个新的线程处理新接入的客户端链路
 * 一个线程只能处理一个客户端连接
 * <p>
 * 无法满足高性能、高并发接入的场景
 * Created by txw on 2017/11/15.
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 6005;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("时间服务器启动,端口号为:" + port);
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                System.out.println("时间服务器关闭");
                serverSocket.close();
                serverSocket = null;
            }

        }

    }


}
