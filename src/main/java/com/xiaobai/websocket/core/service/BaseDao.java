package com.xiaobai.websocket.core.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaobai.websocket.core.entity.BaseIdEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseDao<T extends BaseIdEntity> extends BaseMapper<T> {

}
