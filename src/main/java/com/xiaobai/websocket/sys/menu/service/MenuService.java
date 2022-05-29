package com.xiaobai.websocket.sys.menu.service;

import com.xiaobai.websocket.core.service.BaseService;
import com.xiaobai.websocket.sys.menu.entity.Menu;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public interface MenuService extends BaseService<Menu> {
    Map<String, List<Menu>> listGroup();
}
