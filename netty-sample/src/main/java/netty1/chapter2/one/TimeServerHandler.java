package netty1.chapter2.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * 业务处理
 * Created by txw on 2017/11/16.
 */
public class TimeServerHandler implements Runnable {
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        BufferedReader input = null;
        PrintWriter out = null;
        try {
            input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            while (true) {
                body = input.readLine();
                if (body == null) {
                    break;
                }
                System.out.println("时间服务器收到内容为:" + body);
                if ("query time order".equalsIgnoreCase(body)) {
                    currentTime = new Date(System.currentTimeMillis()).toString();
                } else {
                    currentTime = "bad order";
                }
                out.println(currentTime);
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                this.socket = null;
            }
        }

    }
}
