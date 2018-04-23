package netty1.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * WebSocket服务端：Netty实现
 * Created by txw on 2017/11/28.
 */
public class WebSocketServer {


    public static void main(String[] args) {
        int port = 8080;
        new WebSocketServer().run(port);
    }


    public void run(int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //将请求和应答消息编码或者解码为HTTP消息
                            ch.pipeline().addLast("http-codec", new HttpServerCodec());
                            //将HTTP消息的的多个部分组合成一条完整的HTTP消息
                            ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
                            //
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            //
                            ch.pipeline().addLast("handler", new WebSocketServerHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println("WebSocket Server已经启动，端口号为:" + port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}
