package com.xiaobai.websocket.sys.menu.controller;

import com.xiaobai.websocket.sdk.convert.ApiResponse;
import com.xiaobai.websocket.sys.menu.entity.Menu;
import com.xiaobai.websocket.sys.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ApiResponse<Map<String, List<Menu>>> list() {
        return ApiResponse.success(menuService.listGroup());
    }

    @RequestMapping("/add")
    public ApiResponse<Boolean> add(@RequestBody Menu menu) {
        return ApiResponse.success(menuService.save(menu));
    }
}
