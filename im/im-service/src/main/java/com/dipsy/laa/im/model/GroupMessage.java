package com.dipsy.laa.im.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 群聊消息
 */
@Data
public class GroupMessage implements Serializable {

    private String from;
    private MsgType msgType;
    private String content;
    private Date time;
}
