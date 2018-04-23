package netty1.chapter2.nio;

/**
 * NIO 时间服务器 客户端
 * Created by txw on 2017/11/17.
 */
public class NioTimeClient {

    public static void main(String[] args) {
        int port = 6005;
        if (args != null && args.length > 1) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        new Thread(new NioTimeClientHandler("127.0.0.1", port), "TimeClient-001").start();
    }
}
