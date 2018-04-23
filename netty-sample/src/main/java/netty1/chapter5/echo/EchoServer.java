package netty1.chapter5.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * 以分隔符作为码流结束标识的消息的解码
 * 使用DelimiterBasedFrameDecoder
 * <p>
 * 时间服务器：netty实现  解决TCP粘包拆包问题
 * <p>
 * Created by txw on 2017/11/23.
 */
public class EchoServer {

    public void bind(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                            //
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));

                            ch.pipeline().addLast(new StringDecoder());
                            //
                            ch.pipeline().addLast(new EchoServerHandler());

                        }
                    });
            ChannelFuture future = serverBootstrap.bind(port).sync();
            System.out.println("echo server start , port is " + port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) {
        int port = 9005;
        new EchoServer().bind(port);
    }

}
