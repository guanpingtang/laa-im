package com.dipsy.laa.im.transport.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 处理链
 * @author tgp
 */
public class ImServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //在这里添加处理链 handel链
        // netty自带http请求编解码器
        pipeline.addLast(new HttpServerCodec());
        // 写文件内容
        pipeline.addLast(new ChunkedWriteHandler());
        // netty自带聚合解码 HttpRequest/HttpContent/LastHttpContent到FullHttpRequest,保证接收的Http请求的完整性
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        // netty自带websocket协议处理. websocket协议是建立在http协议基础之上的，开始会先通过http发送请求，
        // 请求头里会有Upgrade:websocket，表示将http升级为websocket.
        pipeline.addLast(new WebSocketServerProtocolHandler("/laa", true));
        pipeline.addLast(new ImTokenHandler());
        // 处理 TextWebSocketFrame
        pipeline.addLast(new ProtocolCodecHandler());
        pipeline.addLast(new AcceptorHandler());
    }
}
