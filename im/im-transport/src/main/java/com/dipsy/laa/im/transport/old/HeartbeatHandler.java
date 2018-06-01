package com.dipsy.laa.im.transport.old;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 心跳处理
 *
 * @author tgp
 */
@Slf4j
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    private int lossConnectCount = 0;

    /**
     * 当一个Channel注册到EventLoop上，可以处理I/O时被调用
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    /**
     * 当一个Channel从它的EventLoop上解除注册，不再处理I/O时被调用
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    /**
     * 当Channel变成活跃状态时被调用；Channel是连接/绑定、就绪的
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    /**
     * 当Channel离开活跃状态，不再连接到某个远端时被调用
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    /**
     * 当channel在指定时间内都没有发生write事件时被调用
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info("已经第{}次6秒没有收到{}的消息了", lossConnectCount, ctx.channel().id());
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                lossConnectCount++;
                if (lossConnectCount > 2) {
                    log.info("已经第3次6秒没有收到{}的消息了,关闭这个链路", ctx.channel().id());
                    ctx.channel().close();
                }
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    /**
     *
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        lossConnectCount = 0;
        log.info("收到来自{}的心跳包,重新开始计数", ctx.channel().id());
    }
}
