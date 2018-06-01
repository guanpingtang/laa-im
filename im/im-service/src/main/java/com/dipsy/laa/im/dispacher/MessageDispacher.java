package com.dipsy.laa.im.dispacher;

import com.dipsy.laa.im.event.EventEnum;
import com.dipsy.laa.im.transport.packet.MessagePacket;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息分发服务
 * 传输模块得到的数据包会放在内存队列中,本服务会从内存队列中取得消息包，并分发给对应的服务执行.
 */
@Slf4j
public class MessageDispacher {

    public static void dispach(MessagePacket messagePacket) {

        int type = messagePacket.getType();

        if (type == EventEnum.AUTH_REQ.getValue()) {
            log.info("登录请求");
        } else if (type == EventEnum.CHAT_REQ.getValue()) {
            log.info("p2p请求");
        } else if (type == EventEnum.GROUP_CHAT_REQ.getValue()) {
            log.info("群聊请求");
        }

        // 释放buffer
        ReferenceCountUtil.release(messagePacket);
    }
}
