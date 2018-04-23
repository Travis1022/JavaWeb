package netty1.chapter5.echo;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by txw on 2017/11/23.
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private int count;

    private final static String request = "zbc,zbc,zbc,zbc.$_";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 15; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(request.getBytes()));
        }
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("This is " + ++count + " times receive server : [" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
