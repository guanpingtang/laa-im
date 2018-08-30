package com.dipsy.laa.im.transport.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.websocketx.Utf8FrameValidator;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

///**
// * 自定义WebSocketServerProtocolHandle,为了自定义握手逻辑
// *
// * @author tgp
// */
//public class MyMyWebSocketServerProtocolHandler extends WebSocketServerProtocolHandler {
//
//    public MyMyWebSocketServerProtocolHandler(String websocketPath) {
//        super(websocketPath);
//    }
//
//    /**
//     * 重写，自定义握手逻辑
//     * @param ctx
//     */
//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) {
//        ChannelPipeline cp = ctx.pipeline();
//        if (cp.get(MyWebSocketServerProtocolHandshakeHandler.class) == null) {
//            // Add the WebSocketHandshakeHandler before this one.
//            ctx.pipeline().addBefore(ctx.name(), MyWebSocketServerProtocolHandshakeHandler.class.getName(),
//                new MyWebSocketServerProtocolHandshakeHandler());
//        }
//        if (cp.get(Utf8FrameValidator.class) == null) {
//            // Add the UFT8 checking before this one.
//            ctx.pipeline().addBefore(ctx.name(), Utf8FrameValidator.class.getName(), new Utf8FrameValidator());
//        }
//    }
//}
