package netty1.chapter5.fixed;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 定长的消息的解码
 * FixedLengthFrameDecoder
 * <p>
 * 时间服务器：netty实现  解决TCP粘包拆包问题  客户端使用TelNet来模拟
 * <p>
 * Created by txw on 2017/11/24.
 */
public class FixedEchoServer {

    public void bind(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //
                            ch.pipeline().addLast(new FixedLengthFrameDecoder(20));
                            //
                            ch.pipeline().addLast(new StringDecoder());
                            //
                            ch.pipeline().addLast(new FixedEchoServerHandler());
                        }
                    });
            ChannelFuture future = serverBootstrap.bind(port).sync();
            System.out.println("FixedTimeServer start at port :" + port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) {
        int port = 9005;
        new FixedEchoServer().bind(port);
    }
}
