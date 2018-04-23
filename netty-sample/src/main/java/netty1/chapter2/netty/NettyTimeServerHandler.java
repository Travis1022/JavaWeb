package netty1.chapter2.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * Created by txw on 2017/11/22.
 */
public class NettyTimeServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;
        byte[] request = new byte[buffer.readableBytes()];
        buffer.readBytes(request);
        String body = new String(request, "UTF-8");
        System.out.println("时间服务器收到的内容为：" + body);
        String currentTime;
        if ("query time order".equalsIgnoreCase(body)) {
            currentTime = new Date(System.currentTimeMillis()).toString();
        } else {
            currentTime = "bad order";
        }
        ByteBuf response = Unpooled.copiedBuffer(currentTime.getBytes());
        // 把待发送的消息放到 发送缓冲数组中
        ctx.write(response);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将发送缓冲区中的消息全部写入到SocketChannel中
        ctx.flush();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
