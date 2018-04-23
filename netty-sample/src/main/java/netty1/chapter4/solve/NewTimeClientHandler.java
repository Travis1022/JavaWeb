package netty1.chapter4.solve;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by txw on 2017/11/23.
 */
public class NewTimeClientHandler extends ChannelInboundHandlerAdapter {

    private int count;

    private byte[] request;

    public NewTimeClientHandler() {
        String str = "query time order" + System.getProperty("line.separator");
        request = str.getBytes();
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
        String body = (String) msg;
        System.out.println("Now is :" + body);
        System.out.println("the count is : " + ++count);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
