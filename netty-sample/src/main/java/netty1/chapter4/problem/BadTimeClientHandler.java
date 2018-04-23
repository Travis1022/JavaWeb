package netty1.chapter4.problem;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

/**
 * Created by txw on 2017/11/23.
 */
public class BadTimeClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger.getLogger(BadTimeClientHandler.class.getName());

    private int count;
    private byte[] request;


    public BadTimeClientHandler() {
        String string = "query time order" + System.getProperty("line.separator");
        request = string.getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(request.length);
            message.writeBytes(request);
            ctx.writeAndFlush(message);
        }
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] request = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(request);
        String body = new String(request, "UTF-8");
        System.out.println("Now is : " + body);
        System.out.println(" the count is :" + ++count);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning(cause.getMessage());
        ctx.close();
    }
}
