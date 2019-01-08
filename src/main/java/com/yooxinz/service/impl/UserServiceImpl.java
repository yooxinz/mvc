package com.yooxinz.service.impl;

import com.yooxinz.dto.User;
import com.yooxinz.mapper.UserMapper;
import com.yooxinz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by star on 2018/9/23.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserNameById(Long userId) {
        return userMapper.getUserNameById(userId);
    }

    @Override
    public List<User> query() {
        return userMapper.query();
    }
}
