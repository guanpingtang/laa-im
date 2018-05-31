package com.dipsy.laa.im.transport.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * 处理链
 * @author tgp
 */
public class ImServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        //在这里添加处理链 handel链
        channelPipeline.addLast(new ProtocolDecoderHandler());
        channelPipeline.addLast(new ProtocolEncoderHandler());
        //channelPipeline.addLast(new IdleStateHandler(6, 0, 0));
        //channelPipeline.addLast(new HeartbeatHandler());
        channelPipeline.addLast(new AcceptorHandler());
    }
}
