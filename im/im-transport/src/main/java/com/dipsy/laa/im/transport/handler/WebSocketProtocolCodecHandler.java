package com.dipsy.laa.im.transport.handler;

import com.dipsy.laa.im.transport.packet.MessagePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 自定义协议编解码
 *
 * @author tgp
 */
public class WebSocketProtocolCodecHandler extends MessageToMessageCodec<TextWebSocketFrame, MessagePacket> {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    protected void encode(ChannelHandlerContext ctx, MessagePacket protocolModel, List<Object> list) throws Exception {
        ObjectNode root = jsonMapper.createObjectNode();
        JsonNode body = jsonMapper.readTree(protocolModel.getBody());

        root.put("version", protocolModel.getVersion());
        root.put("type", protocolModel.getType());
        root.set("body", body);

        list.add(new TextWebSocketFrame(root.toString()));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, TextWebSocketFrame frame, List<Object> list) throws Exception {
        String text = frame.text();
//        JsonNode root = jsonMapper.readTree(text);
        MessagePacket protocolModel = new MessagePacket();
        protocolModel.setBody(text.getBytes());
//        if (root.has("version")) {
//            protocolModel.setVersion(root.get("version").shortValue());
//        }
//        if (root.has("type")) {
//            protocolModel.setType(root.get("commandId").asInt());
//        }
//        if (root.has("body")) {
//            protocolModel.setBody(root.get("body").toString().getBytes());
//        }
        list.add(protocolModel);
    }
}