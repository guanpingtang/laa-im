package com.dipsy.laa.im.transport.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

/**
 * im客户端启动
 *
 * @author tgp
 */
public class ImClientStart {

    private static final String host = "127.0.0.1";
    private static final int port = 9432;
    /// 通过nio方式来接收连接和处理连接
    private static EventLoopGroup group = new NioEventLoopGroup();
    private static Bootstrap b = new Bootstrap();
    private static Channel ch;

    /**
     * Netty创建全部都是实现自AbstractBootstrap。
     * 客户端的是Bootstrap，服务端的则是ServerBootstrap。
     **/
    public static void main(String[] args) throws InterruptedException, IOException {
        b
            .group(group)
            .channel(NioSocketChannel.class)
            .handler(new ImClientChannelInitializer());
        ch = b.connect(host, port).sync().channel();
        send();
    }

    private static void send() throws IOException {
        String str = "Hello Netty";
        ch.writeAndFlush(str);
        System.out.println("客户端发送数据:" + str);
    }

}
