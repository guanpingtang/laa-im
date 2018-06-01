package com.dipsy.laa.im.transport.queue;

import com.dipsy.laa.im.transport.packet.MessagePacket;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 接收阻塞队列，缓存刚入站的消息.
 *
 * Transport Module ---> InboundQueue ---> Service Module.
 *
 * @author tgp
 */
public class MessageBlockQueue {

    private volatile static BlockingQueue<MessagePacket> protocolModelBlockingQueue;

    public static BlockingQueue<MessagePacket> getQueue() {
        if (protocolModelBlockingQueue == null) {
            synchronized (MessageBlockQueue.class) {
                if (protocolModelBlockingQueue == null) {
                    protocolModelBlockingQueue = new LinkedBlockingDeque<>(1024);
                }
            }
        }
        return protocolModelBlockingQueue;
    }
}
