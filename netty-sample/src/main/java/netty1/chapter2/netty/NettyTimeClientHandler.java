package netty1.chapter2.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2017/11/22.
 */
public class NettyTimeClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger.getLogger(NettyTimeClientHandler.class.getName());

    private final ByteBuf firstMessage;

    public NettyTimeClientHandler() {
        String string = "query time order";
        byte[] request = string.getBytes();
        firstMessage = Unpooled.buffer(request.length);
        firstMessage.writeBytes(request);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        //发送消息给服务端
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //服务端返回的消息
        ByteBuf buf = (ByteBuf) msg;
        byte[] response = new byte[buf.readableBytes()];
        buf.readBytes(response);
        String body = new String(response, "UTF-8");
        System.out.println("Now is :" + body);

    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印异常日志,并且释放资源
        logger.warning("Exception " + cause.getMessage());
        ctx.close();
    }
}
