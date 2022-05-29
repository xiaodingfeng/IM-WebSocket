package com.xiaobai.websocket.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseIdEntity implements Serializable {
    @TableField("ID")
    private Long id;
}
