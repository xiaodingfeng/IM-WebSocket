package com.xiaobai.websocket.core.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaobai.websocket.core.entity.BaseIdEntity;

public abstract class BaseServiceImpl<M extends BaseDao<T>, T extends BaseIdEntity> extends ServiceImpl<M, T> implements BaseService<T>  {
}
