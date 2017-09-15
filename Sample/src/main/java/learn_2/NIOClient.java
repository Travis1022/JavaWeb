package learn_2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by txw on 2017/9/14.
 */
public class NIOClient {
    private final String host;
    private final int port;

    public NIOClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        //用来处理连接、接受数据、发送数据
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            //创建Bootstrap对象用来引导启动客户端
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //客户端连接服务器之后就会被执行
                            ch.pipeline().addLast(new NIOClientHandler());
                        }
                    });
            //连接服务器
            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        } finally {
            //释放资源
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new NIOClient("localhost", 65535).start();
    }

}
