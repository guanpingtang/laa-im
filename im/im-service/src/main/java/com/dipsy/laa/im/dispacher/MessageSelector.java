package com.dipsy.laa.im.dispacher;


import com.dipsy.laa.im.transport.protocol.MessageHolder;
import com.dipsy.laa.im.transport.queue.MessageQueue;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 消息轮询器
 */
@Slf4j
public class MessageSelector {

    private static AtomicBoolean shutdown = new AtomicBoolean(false);

    //消息队列 java自己的消息队列
    private BlockingQueue<MessageHolder> messageHolderBlockingQueue;
    //只有一个线程的线程池,负责在消息队列中取消息.
    private ExecutorService singleExecutor;
    //多个线程的线程池,负责把消息处理.
    private ExecutorService workExecutor;

    public MessageSelector() {
        init();
    }

    private void init() {
        singleExecutor = Executors.newSingleThreadExecutor();
        workExecutor = Executors.newFixedThreadPool(10);
        messageHolderBlockingQueue = MessageQueue.getQueue();
        log.info("消息轮询器初始化成功");
    }

    //todo 可使用spring boot 线程池来处理。
    public void start() {
        singleExecutor.execute(new Runnable() {
            @Override
            public void run() {
                while (!shutdown.get()) {
                    try {
                        MessageHolder messageHolder = messageHolderBlockingQueue.take();
                        startTask(messageHolder);
                    } catch (InterruptedException e) {
                        log.warn("取消息时遇到错误:{}", e.getMessage());
                    }
                }
            }

            private void startTask(MessageHolder messageHolder) {
                workExecutor.execute(() -> {
                    log.info("开始执行取出的任务:{}", messageHolder.toString());
                    MessageDispacher.dispach(messageHolder);
                });
            }
        });
    }
}
