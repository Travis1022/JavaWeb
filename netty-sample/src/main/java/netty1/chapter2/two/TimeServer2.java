package netty1.chapter2.two;

import netty1.chapter2.one.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪异步IO
 * 时间服务器:采用线程池和队列实现
 * Created by txw on 2017/11/16.
 */
public class TimeServer2 {
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
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);
            while (true) {
                socket = serverSocket.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
                serverSocket = null;
                System.out.println("时间服务器2关闭");
            }
        }
    }



}
