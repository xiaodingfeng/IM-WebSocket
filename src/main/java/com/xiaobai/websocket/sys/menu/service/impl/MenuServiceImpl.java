package com.xiaobai.websocket.sys.menu.service.impl;

import com.xiaobai.websocket.core.service.BaseServiceImpl;
import com.xiaobai.websocket.sys.menu.entity.Menu;
import com.xiaobai.websocket.sys.menu.mapper.MenuMapper;
import com.xiaobai.websocket.sys.menu.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements MenuService {
    @Override
    public Map<String, List<Menu>> listGroup() {
        return baseMapper.list().stream().collect(Collectors.groupingBy(Menu::getCName));
    }
}
