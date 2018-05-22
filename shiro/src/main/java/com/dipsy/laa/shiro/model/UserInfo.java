package com.dipsy.laa.shiro.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户model
 * @author tgp
 */
@TableName("user_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends Model<UserInfo> {

    @TableField("user_id")
    private String userId;

    @TableField("user_account")
    private String userAccount;
    private String password;
    private String cellphone;
    private String email;
    private String nickname;
    @TableField("create_at")
    private Date createAt;
    @TableField("update_at")
    private Date updateAt;

    /**
     * 主键值
     */
    @Override
    protected Serializable pkVal() {
        return userId;
    }
}
