package netty1.chapter2.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


/**
 * 服务端IO事件处理类
 * 处理网络IO事件,例如记录日志、对消息进行编解码
 * Created by txw on 2017/11/22.
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new NettyTimeServerHandler());
    }
}
