package netty1.chapter5.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * 客户端
 * Created by txw on 2017/11/23.
 */
public class EchoClient {

    public void connect(String host, int port) {
        EventLoopGroup loopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                            //
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));

                            ch.pipeline().addLast(new StringDecoder());
                            //
                            ch.pipeline().addLast(new EchoClientHandler());

                        }
                    });
            ChannelFuture future = bootstrap.connect(host,port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            loopGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        int port = 9005;
        new EchoClient().connect("127.0.0.1", port);

    }
}
