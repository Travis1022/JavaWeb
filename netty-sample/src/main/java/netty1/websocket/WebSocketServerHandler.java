package netty1.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;

/**
 * 业务逻辑处理
 * Created by txw on 2017/11/28.
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger logger = Logger.getLogger(WebSocketServerHandler.class.getName());
    private WebSocketServerHandshaker handShaker;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //传统的HTTP接入
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        } else {
            logger.info("未知类型");
        }
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public boolean acceptInboundMessage(Object msg) throws Exception {
        return super.acceptInboundMessage(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning(cause.getMessage());
    }

    /**
     * 处理WebSocket接入
     *
     * @param ctx
     * @param frame
     */
    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        //判断是否是关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handShaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        //判断是否是Ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        //仅支持文本消息
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(String.format("%s frame types not supported"
                    , frame.getClass().getName()));
        }
        //返回应答消息
        String request = ((TextWebSocketFrame) frame).text();
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(String.format("%s received %s", ctx.channel(), request));
        }
        TextWebSocketFrame text = new TextWebSocketFrame(request + " ,欢迎使用Netty WebSocket服务,现在时刻为:"
                + new java.util.Date().toString());
        ctx.channel().write(text);
    }

    /**
     * 处理HTTP接入
     *
     * @param ctx
     * @param request
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        //如果HTTP解码失败,返回HTTP异常
        if (!request.getDecoderResult().isSuccess() || (!"websocket".equals(request.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.BAD_REQUEST));
        }
        //构造握手响应返回
        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(
                "ws://localhost:8080/web-socket",
                null,
                false
        );
        handShaker = factory.newHandshaker(request);
        if (handShaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handShaker.handshake(ctx.channel(), request);
        }
    }

    /**
     * @param ctx
     * @param request
     * @param response
     */
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response) {
        //返回应答给客户端
        if (response.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(response.getStatus().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();
            setContentLength(response, response.content().readableBytes());
        }
        //如果是非keep-Alive,关闭连接
        ChannelFuture future = ctx.channel().writeAndFlush(response);
        if (!isKeepAlive(response) || response.getStatus().code() != 200) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

}
