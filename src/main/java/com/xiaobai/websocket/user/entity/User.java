package com.xiaobai.websocket.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@TableName("`USER`")
public class User {
    @TableField("ID")
    private Long id;

    @NotBlank(message = "用户名不为空")
    @TableField("USER_NAME")
    private String userName;

    @NotBlank(message = "密码不为空")
    @TableField("PASSWORD")
    private String password;

    @TableField("STATUS")
    private Integer status;
}
