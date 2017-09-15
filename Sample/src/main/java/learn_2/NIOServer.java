package learn_2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by txw on 2017/9/13.
 */
public class NIOServer {
    private final int port;

    public NIOServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            // create ServerBootStrap instance
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // specifies NIO transport, local socket address
            // add handler to channel pipeline
            serverBootstrap.group(group)
                    //指定通道类型
                    .channel(NioServerSocketChannel.class)
                    //监听地址(或者某个端口)
                    .localAddress(port)
                    //调用childHandler放指定连接后调用的ChannelHandler
                    .childHandler(new ChannelInitializer<Channel>() {
                        protected void initChannel(Channel ch) throws Exception {
                            //设置ChannelHandler
                            ch.pipeline().addLast(new NIOServerHandler());
                        }
                    });
            //Binds server, wait for server to close, and release resources
            ChannelFuture future = serverBootstrap.bind().sync();
            System.out.println(NIOServer.class.getName() + " started and listen on " + future.channel().localAddress());
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new NIOServer(65535).start();
    }



}
