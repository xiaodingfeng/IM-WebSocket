package com.xiaobai.websocket.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaobai.websocket.core.entity.BaseIdEntity;

public interface BaseService<T extends BaseIdEntity> extends IService<T> {
}

