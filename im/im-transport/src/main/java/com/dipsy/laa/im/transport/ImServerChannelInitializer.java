package com.dipsy.laa.im.transport;

import com.dipsy.laa.im.transport.handler.ProtocolDecoderHandler;
import com.dipsy.laa.im.transport.handler.ProtocolEncoderHandler;
import com.dipsy.laa.im.transport.handler.AcceptorHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 入站处理链
 * @author tgp
 */
public class ImServerChannelInitializer extends SimpleChannelInboundHandler {

    /**
     * <strong>Please keep in mind that this method will be renamed to
     * {@code messageReceived(ChannelHandlerContext, I)} in 5.0.</strong>
     * <p>
     * Is called for each message of type {@link I}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ChannelPipeline channelPipeline = ctx.pipeline();
        //在这里添加处理链 handel链
        channelPipeline.addLast(new ProtocolDecoderHandler());
        channelPipeline.addLast(new ProtocolEncoderHandler());
        //channelPipeline.addLast(new IdleStateHandler(6, 0, 0));
        //channelPipeline.addLast(new HeartbeatHandler());
        channelPipeline.addLast(new AcceptorHandler());
    }

}
