//package com.dipsy.laa.im.transport.handler;
//
//import io.netty.channel.*;
//import io.netty.handler.codec.http.*;
//import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
//import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
//import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
//import io.netty.handler.ssl.SslHandler;
//
//import static io.netty.handler.codec.http.HttpMethod.GET;
//import static io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN;
//import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;
//import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
//
///**
// * 自定义WebSocketServerProtocolHandle,为了握手自定义逻辑
// *
// * @author tgp
// */
//public class MyWebSocketServerProtocolHandshakeHandler extends ChannelInboundHandlerAdapter {
//
//    MyWebSocketServerProtocolHandshakeHandler() {
//
//    }
//
//    @Override
//    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
//        final FullHttpRequest req = (FullHttpRequest) msg;
//        if (isNotWebSocketPath(req)) {
//            ctx.fireChannelRead(msg);
//            return;
//        }
//
//        try {
//            if (req.method() != GET) {
//                sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, FORBIDDEN));
//                return;
//            }
//
//            final WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
//                getWebSocketLocation(ctx.pipeline(), req, ""),"", true);
//            final WebSocketServerHandshaker handshaker = wsFactory.newHandshaker(req);
//            if (handshaker == null) {
//                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
//            } else {
//                final ChannelFuture handshakeFuture = handshaker.handshake(ctx.channel(), req);
//                handshakeFuture.addListener(new ChannelFutureListener() {
//                    @Override
//                    public void operationComplete(ChannelFuture future) throws Exception {
//                        if (!future.isSuccess()) {
//                            ctx.fireExceptionCaught(future.cause());
//                        } else {
//                            // Kept for compatibility
//                            ctx.fireUserEventTriggered(
//                                WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE);
//                            ctx.fireUserEventTriggered(
//                                new WebSocketServerProtocolHandler.HandshakeComplete(
//                                    req.uri(), req.headers(), handshaker.selectedSubprotocol()));
//                        }
//                    }
//                });
//                WebSocketServerProtocolHandler.setHandshaker(ctx.channel(), handshaker);
//                ctx.pipeline().replace(this, "WS403Responder",
//                    WebSocketServerProtocolHandler.forbiddenHttpRequestResponder());
//            }
//        } finally {
//            req.release();
//        }
//    }
//
//    private boolean isNotWebSocketPath(FullHttpRequest req) {
//        return checkStartsWith ? !req.uri().startsWith(websocketPath) : !req.uri().equals(websocketPath);
//    }
//
//    private static void sendHttpResponse(ChannelHandlerContext ctx, HttpRequest req, HttpResponse res) {
//        ChannelFuture f = ctx.channel().writeAndFlush(res);
//        if (!isKeepAlive(req) || res.status().code() != 200) {
//            f.addListener(ChannelFutureListener.CLOSE);
//        }
//    }
//
//    private static String getWebSocketLocation(ChannelPipeline cp, HttpRequest req, String path) {
//        String protocol = "ws";
//        if (cp.get(SslHandler.class) != null) {
//            // SSL in use so use Secure WebSockets
//            protocol = "wss";
//        }
//        String host = req.headers().get(HttpHeaderNames.HOST);
//        return protocol + "://" + host + path;
//    }
//}
