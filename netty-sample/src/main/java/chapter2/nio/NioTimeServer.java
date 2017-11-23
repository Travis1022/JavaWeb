package chapter2.nio;

/**
 * 时间服务器：NIO
 * Created by txw on 2017/11/16.
 */
public class NioTimeServer {
    public static void main(String[] args) {
        int port = 6005;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        MultipleTimeServer multipleTimeServer = new MultipleTimeServer(port);
        new Thread(multipleTimeServer, "NIO MultipleTimeServer Start").start();
    }

}

