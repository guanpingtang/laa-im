package com.dipsy.laa.im.transport.handler;

import com.dipsy.laa.common.util.BeanUtils;
import com.dipsy.laa.common.util.JWTUtil;
import com.dipsy.laa.shiro.model.UserInfo;
import com.dipsy.laa.shiro.service.UserService;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * 自定义token校验处理
 * @author tgp
 */
public class ImTokenHandler extends ChannelInboundHandlerAdapter {

    private UserService userService = BeanUtils.getBean(UserService.class);

    /**
     * Calls {@link ChannelHandlerContext#fireUserEventTriggered(Object)} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     * @param evt
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            WebSocketServerProtocolHandler.HandshakeComplete complete = (WebSocketServerProtocolHandler.HandshakeComplete) evt;
//          String token = complete.requestHeaders().get("token");
            String token = complete.requestUri().substring(11);
            String userId = JWTUtil.getUserId(token);
            UserInfo userInfo = userService.findByUserId(userId);
            if (userInfo == null) {
                ctx.pipeline().replace(this, "WS403Responder", ImTokenHandler.forbiddenHttpRequestResponder());
            }
        } else {
            ctx.fireUserEventTriggered(evt);
        }
    }

    static ChannelHandler forbiddenHttpRequestResponder() {
        return new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                if (msg instanceof FullHttpRequest) {
                    ((FullHttpRequest) msg).release();
                    FullHttpResponse response =
                        new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.FORBIDDEN);
                    ctx.channel().writeAndFlush(response);
                } else {
                    ctx.fireChannelRead(msg);
                }
            }
        };
    }
}
