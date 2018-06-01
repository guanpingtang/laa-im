package com.dipsy.laa.im.transport.old;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * 入站简单处理 example 无逻辑
 */
@Slf4j
public class ClientSimpleHandle extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("客户端：链接服务器成功");
        MessageHolder messageHolder = new MessageHolder();
        messageHolder.setSign(ProtocolHeader.REQUEST);
        messageHolder.setType(ProtocolHeader.PERSON_MESSAGE);
        messageHolder.setBody("Hello Netty");
        ctx.writeAndFlush(messageHolder);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.debug("客户端：链接关闭");
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof MessageHolder) {
            MessageHolder messageHolder = (MessageHolder) msg;
            System.out.println("客户端接受的消息: " + messageHolder.toString());
        } else {
            throw new IllegalArgumentException("msg is not instance of MessageHolder");
        }
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

}
