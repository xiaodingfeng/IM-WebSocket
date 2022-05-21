package com.xiaobai.websocket.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaobai.websocket.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
