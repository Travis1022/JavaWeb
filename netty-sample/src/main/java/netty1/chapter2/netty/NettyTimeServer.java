package netty1.chapter2.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 时间服务器：使用NIO框架Netty
 * Created by txw on 2017/11/22.
 */
public class NettyTimeServer {

    public void bind(int port) throws Exception {
        //配置服务端的NIO线程组(用于服务端接受客户端的连接)
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //配置服务端的NIO线程组(用于进行SocketChannel的网络读写)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());

            //绑定端口,同步等待成功
            ChannelFuture future = serverBootstrap.bind(port).sync();

            System.out.println("服务端已经启动：端口号为" + port);

            //等待服务端监听端口关闭
            future.channel().closeFuture().sync();
        } finally {
            //退出,释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 9005;
        if (args != null && args.length > 1) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        new NettyTimeServer().bind(port);
    }

}
