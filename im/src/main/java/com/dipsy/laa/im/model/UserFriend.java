package com.dipsy.laa.im.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 联系人
 * @author tgp
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_friends")
public class UserFriend extends Model<UserFriend> {

    private String id;

    @TableField("user_id")
    private String userId;

    @TableField("friend_id")
    private String friendId;

    @TableField("create_at")
    private Date createAt;

    @TableField("update_at")
    private Date updateAt;

    /**
     * 主键值
     */
    @Override
    protected Serializable pkVal() {
        return id;
    }

}
