package netty1.chapter10;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 文件服务器: Netty
 * Created by txw on 2017/11/27.
 */
public class HttpFileServer {

    private static final String IP = "127.0.0.1";
    private static final String URL = "/src/com/java";
    private static final int PORT = 8080;


    public static void main(String[] args) {
        new HttpFileServer().run(IP, URL, PORT);
    }

    public void run(String ip, final String url, int port) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //Http请求消息解码器
                            ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                            //将多个消息转化成单一的FullHttpRequest 或者 FullHttpResponse
                            ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            //Http响应解码器
                            ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
                            //支持异步发送大的码流，但不占用过多的内存
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            //业务逻辑
                            ch.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));

                        }
                    });
            ChannelFuture future = serverBootstrap.bind(ip, port).sync();
            System.out.println("文件服务器已经启动,地址为:" + ip + ":" + port + url);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
