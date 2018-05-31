package com.dipsy.laa.im.transport.queue;

import com.dipsy.laa.im.transport.protocol.MessageHolder;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 接收阻塞队列，缓存刚入站的消息.
 *
 * Transport Module ---> InboundQueue ---> Service Module.
 *
 * @author tgp
 */
public class MessageQueue {

    private volatile static BlockingQueue<MessageHolder> messageHolderBlockingQueue;

    public static BlockingQueue<MessageHolder> getQueue() {
        if (messageHolderBlockingQueue == null) {
            synchronized (MessageQueue.class) {
                if (messageHolderBlockingQueue == null) {
                    messageHolderBlockingQueue = new LinkedBlockingDeque<>(1024);
                }
            }
        }
        return messageHolderBlockingQueue;
    }
}
