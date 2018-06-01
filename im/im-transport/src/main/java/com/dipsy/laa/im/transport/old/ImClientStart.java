package com.dipsy.laa.im.transport.old;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

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

    /**
     * Netty创建全部都是实现自AbstractBootstrap。
     * 客户端的是Bootstrap，服务端的则是ServerBootstrap。
     **/
    public static void main(String[] args) {
        try {
            b
                .group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ImClientChannelInitializer());
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }

    }

}
