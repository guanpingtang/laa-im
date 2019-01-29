package com.dipsy.laa.im.transport.handler;


import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author tgp
 */
public class ImTokenHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
        final FullHttpRequest request = (FullHttpRequest) msg;

        try {
            request.uri();

        } finally {
            request.release();
        }
    }
}
