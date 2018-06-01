package com.dipsy.laa.im.transport.handler;

import com.dipsy.laa.im.transport.packet.MessagePacket;
import com.dipsy.laa.im.transport.queue.MessageBlockQueue;
import io.netty.channel.*;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

/**
 * 接受处理
 */
@Slf4j
public class AcceptorHandler extends SimpleChannelInboundHandler<MessagePacket> {

    private final BlockingQueue<MessagePacket> taskQueue;

    public AcceptorHandler() {
        this.taskQueue = MessageBlockQueue.getQueue();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessagePacket messagePacket) throws Exception {
        log.info("服务端接收到客户端消息:{}", messagePacket);
        MessagePacket messagePacket1 = new MessagePacket();
        messagePacket1.setVersion(MessagePacket.CUR_VERSION);
        messagePacket1.setType(2);
        String msg = "我收到了你的消息";
        messagePacket1.setBody(msg.getBytes());
        ctx.writeAndFlush(messagePacket1);
        // 添加到任务队列
        boolean offer = taskQueue.offer(messagePacket);
        if (!offer) {
            // 服务器繁忙
            log.warn("服务器繁忙，拒绝服务");
            // 繁忙响应
        }
    }
    
    @Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	log.info("链路激活");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("服务端监听到客户端退出 ，进行业务处理");

	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("exceptionCaught", cause);
    }
}