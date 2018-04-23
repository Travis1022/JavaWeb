package netty1.chapter2.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * NIO 时间客户端 业务处理
 * Created by txw on 2017/11/17.
 */
public class NioTimeClientHandler implements Runnable {
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;


    /**
     * 初始化NIO的多路复用器和SocketChannel对象
     *
     * @param host
     * @param port
     */
    public NioTimeClientHandler(String host, int port) {
        this.host = host == null ? "127.0.0.1" : host;
        this.port = port;

        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void run() {
        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
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
                        handleInput(key);
                    } catch (Exception e) {
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
                System.exit(1);
            }
        }
        //多路复用器关闭后，所有注册在上面的channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("关闭selector");
            selector = null;
        }

    }


    /**
     * @throws IOException
     */
    private void doConnect() throws IOException {
        //若连接成功，则注册到多路复用器上，发送请求消息，读应答
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    /**
     * @param key
     */
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (socketChannel.finishConnect()) {
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    doWrite(socketChannel);
                } else {
                    System.exit(1);
                }
                //TODO: key.isReadable() 为 FALSE  无法读取服务端返回的内容
                if (key.isReadable()) {
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int readBytes = socketChannel.read(readBuffer);
                    if (readBytes > 0) {
                        readBuffer.flip();
                        byte[] bytes = new byte[readBuffer.remaining()];
                        readBuffer.get(bytes);
                        String body = new String(bytes, "UTF-8");
                        System.out.println("Now is " + body);
                        this.stop = true;
                    } else if (readBytes < 0) {
                        //对端链路关闭
                        key.cancel();
                        socketChannel.close();
                    } else {
                        //读到0字节
                    }
                } else {
                    System.out.println("key.isValid  " + key.isValid());
                    System.out.println("key.isAcceptable  " + key.isAcceptable());
                    System.out.println("key.isConnectable  " + key.isConnectable());
                    System.out.println("key.isReadable  " + key.isReadable());
                    System.out.println("key.isWritable  " + key.isWritable());
                }
            }
        }
    }


    /**
     * @param socketChannel
     */
    private void doWrite(SocketChannel socketChannel) throws IOException {
        byte[] request = "query time order".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(request.length);
        writeBuffer.put(request);
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        if (!writeBuffer.hasRemaining()) {
            System.out.println("Send order 2 server succeed");
        }
    }
}
