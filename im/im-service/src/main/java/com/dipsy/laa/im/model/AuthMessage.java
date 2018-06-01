package com.dipsy.laa.im.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 认证消息模型
 */
@Data
public class AuthMessage implements Serializable {

    private String token;
}
