package com.dipsy.laa.im.dispacher;

import com.dipsy.laa.im.transport.protocol.MessageHolder;
import com.dipsy.laa.im.transport.protocol.ProtocolHeader;
import io.netty.channel.Channel;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息分发服务
 * 传输模块得到的数据包会放在内存队列中,本服务会从内存队列中取得消息包，并分发给对应的服务执行.
 */
@Slf4j
public class MessageDispacher {

    public static void dispacher(MessageHolder messageHolder) {
        if (messageHolder.getSign() != ProtocolHeader.REQUEST) {
            // 请求错误
            response(messageHolder.getChannel(), messageHolder.getSign());
        }

        switch (messageHolder.getType()) {
            // 点对点消息
            case ProtocolHeader.PERSON_MESSAGE:
                //todo 服务器中转消息
                break;
            // 讨论组消息
            case ProtocolHeader.GROUP_MESSAGE:
                //todo 服务器中转消息
                break;

            // 请求错误
            default:
                response(messageHolder.getChannel(), messageHolder.getSign());
                break;
        }

        // 释放buffer
        ReferenceCountUtil.release(messageHolder);
    }


    /**
     * 请求错误响应
     */
    private static void response(Channel channel, byte sign) {
        MessageHolder messageHolder = new MessageHolder();
        messageHolder.setSign(ProtocolHeader.RESPONSE);
        messageHolder.setType(sign);
        messageHolder.setStatus(ProtocolHeader.REQUEST_ERROR);
        messageHolder.setBody("");
        channel.writeAndFlush(messageHolder);
    }
}
