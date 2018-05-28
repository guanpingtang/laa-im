package com.dipsy.laa.im.transport.queue;

import com.dipsy.laa.im.transport.protocol.MessageHolder;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 接收阻塞队列，缓存刚入站的任务.
 *
 * Transport Module ---> InboundQueue ---> Service Module.
 *
 * @author tgp
 */
public class TaskQueue {

    private volatile static BlockingQueue<MessageHolder> queue;

    public static BlockingQueue<MessageHolder> getQueue() {
        if (queue == null) {
            synchronized (TaskQueue.class) {
                if (queue == null) {
                    queue = new LinkedBlockingDeque<>(1024);
                }
            }
        }
        return queue;
    }
}
