package netty1.chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 时间服务器：客户端
 * Created by txw on 2017/11/15.
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 6005;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        Socket socket = null;
        BufferedReader input = null;
        PrintWriter output = null;

        try {
            socket = new Socket("127.0.0.1", port);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("query time order");
            System.out.println("Send order 2 server succeed");
            String response = input.readLine();
            System.out.println("Now is:" + response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
                output = null;
            }
            if (input != null) {
                try {
                    input.close();
                    System.out.println("BufferedReader 关闭");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                input = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
