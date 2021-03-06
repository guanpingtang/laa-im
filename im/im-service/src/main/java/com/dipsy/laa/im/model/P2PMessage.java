package com.dipsy.laa.im.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * p2p
 */
@Data
public class P2PMessage implements Serializable {

    private String from;
    private String to;
    private MsgType msgType;
    private String content;
    private Date time;
}
