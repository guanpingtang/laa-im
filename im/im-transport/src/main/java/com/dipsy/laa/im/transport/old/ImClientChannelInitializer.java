package com.dipsy.laa.im.transport.old;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * 入站处理链
 * @author tgp
 */
public class ImClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        //在这里添加处理链 handel链
        channelPipeline.addLast(new ProtocolDecoderHandler());
        channelPipeline.addLast(new ProtocolEncoderHandler());
        channelPipeline.addLast(new ClientSimpleHandle());
    }
}
