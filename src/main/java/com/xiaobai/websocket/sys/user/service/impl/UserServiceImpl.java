package com.xiaobai.websocket.sys.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.xiaobai.websocket.sdk.exception.BusinessRuntimeException;
import com.xiaobai.websocket.sys.user.entity.User;
import com.xiaobai.websocket.sys.user.mapper.UserMapper;
import com.xiaobai.websocket.sys.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    private String shaPassword(User user) {
        return Base64Utils.encodeToString(DigestUtils.md5Digest((user.getId() + user.getPassword()).getBytes()));
    }

    @Override
    public void addUser(User user) {
        user.setStatus(1);
        User user1 = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, user.getUserName()));

        if (user1 != null) {
            throw new BusinessRuntimeException("-2", "用户已存在");
        }
        userMapper.insert(user);
        user.setPassword(this.shaPassword(user));
        userMapper.update(null, new LambdaUpdateWrapper<User>()
                        .set(User::getPassword, user.getPassword())
                .eq(User::getId, user.getId()));
    }

    @Override
    public void checkUser(User user) {
        User selectOne = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, user.getUserName()));

        if (selectOne == null) {
            throw new BusinessRuntimeException("-4","用户不存在");
        }
        user.setId(selectOne.getId());

        if (Objects.equals(this.shaPassword(user),selectOne.getPassword())) {
            return;
        }
        throw new BusinessRuntimeException("-3","密码错误");
    }
}
