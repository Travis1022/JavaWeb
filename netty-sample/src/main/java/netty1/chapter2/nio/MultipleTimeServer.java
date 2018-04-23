package netty1.chapter2.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * 多路复用类
 * 独立线程：用于轮询多路复用器selector,可以处理多个客户端的并发接入
 * <p>
 * Created by txw on 2017/11/16.
 */
public class MultipleTimeServer implements Runnable {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private volatile boolean stop;

    /**
     * @param port
     */
    public MultipleTimeServer(int port) {

        try {
            //创建多路复用器
            selector = Selector.open();
            //打开ServerSocketChannel,用于监听客户端的连接
            serverSocketChannel = ServerSocketChannel.open();
            //设置连接为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            //绑定监听端口
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            //注册到多路复用器上,监听accept事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("NIO时间服务器已经启动,端口号为:" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public void run() {
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handlerInput(key);
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //多路复用器关闭后，所有注册在上面的channel和pipe等资源都会被自动去注册，所以不需要重复释放资源
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void stop() {
        this.stop = true;
    }


    /**
     * 读取数据
     *
     * @param key
     * @throws IOException
     */
    public void handlerInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) (key.channel());
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = socketChannel.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("NIO时间服务器收到内容:" + body);
                    String currentTime;
                    if ("query time order".equalsIgnoreCase(body)) {
                        currentTime = new Date(System.currentTimeMillis()).toString();
                    } else {
                        currentTime = "bad order";
                    }
                    doWrite(socketChannel, currentTime);
                } else if (readBytes < 0) {
                    //关闭链路
                    key.cancel();
                    socketChannel.close();
                } else {
                    //0字节
                }
            }
        }

    }

    /**
     * 写入数据
     *
     * @param socketChannel
     * @param response
     */
    private void doWrite(SocketChannel socketChannel, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            socketChannel.write(writeBuffer);
        }

    }
}
