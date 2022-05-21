package com.xiaobai.websocket.user.controller;

import com.xiaobai.websocket.sdk.convert.ApiResponse;
import com.xiaobai.websocket.user.entity.User;
import com.xiaobai.websocket.user.service.UserService;
import com.xiaobai.websocket.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiResponse<String> login(@Valid @RequestBody User user) {
        userService.checkUser(user);
        return ApiResponse.success(JwtUtils.getToken(user));
    }

    @PostMapping("/create")
    public ApiResponse<String> create(@Valid @RequestBody User user) {
        userService.addUser(user);
        return ApiResponse.success(JwtUtils.getToken(user));
    }
}
