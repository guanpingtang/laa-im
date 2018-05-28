package com.dipsy.laa.im.transport.handler;

import com.dipsy.laa.im.transport.protocol.MessageHolder;
import com.dipsy.laa.im.transport.protocol.ProtocolHeader;
import com.dipsy.laa.im.transport.queue.TaskQueue;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;


/**
 * 接受处理
 */
@Slf4j
public class AcceptorHandler extends ChannelInboundHandlerAdapter {

    private final BlockingQueue<MessageHolder> taskQueue;

    public AcceptorHandler() {
        taskQueue = TaskQueue.getQueue();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof MessageHolder) {
            MessageHolder messageHolder = (MessageHolder) msg;
            // 指定Channel
            messageHolder.setChannel(ctx.channel());
            // 添加到任务队列
            boolean offer = taskQueue.offer(messageHolder);
            log.info("TaskQueue添加任务: taskQueue=" + taskQueue.size());
            if (!offer) {
                // 服务器繁忙
                log.warn("服务器繁忙，拒绝服务");
                // 繁忙响应
                response(ctx.channel(), messageHolder.getSign());
            }
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

    /**
     * 服务器繁忙响应
     */
    private void response(Channel channel, byte sign) {
        MessageHolder messageHolder = new MessageHolder();
        messageHolder.setSign(ProtocolHeader.RESPONSE);
        messageHolder.setType(sign);
        messageHolder.setStatus(ProtocolHeader.SERVER_BUSY);
        messageHolder.setBody("");
        channel.writeAndFlush(messageHolder);
    }

}
