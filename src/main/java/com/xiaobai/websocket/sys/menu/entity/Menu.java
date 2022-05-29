package com.xiaobai.websocket.sys.menu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaobai.websocket.core.entity.BaseIdEntity;
import lombok.Data;

@Data
@TableName("`MENU`")
public class Menu extends BaseIdEntity {
    @TableField("NAME")
    private String name;
    @TableField("CID")
    private Long cid;
    @TableField(exist = false)
    private String cName;
    @TableField("DISPLAY")
    private Integer display;
    @TableField("URL")
    private String url;
    @TableField("ICO")
    private String ico;
    @TableField("SORT")
    private Integer sort;
    @TableField("STATUS")
    private Integer status;
}
