package com.dipsy.laa.im.transport.packet;

import lombok.Data;

/**
 * 数据包
 */
@Data
public class MessagePacket {

    public static final short CUR_VERSION = 1;

    // 数据包版本
    private short version;
    // 数据包操作类型 对应EventEnum类型
    private int type;
    // 数据包体
    private byte[] body;
}