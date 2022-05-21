package com.xiaobai.websocket.user.service;

import com.xiaobai.websocket.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    void addUser(User user);
    void checkUser(User user);
}
