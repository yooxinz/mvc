package com.yooxinz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yooxinz.common.DateTool;
import com.yooxinz.dto.User;
import com.yooxinz.mapper.UserMapper;
import com.yooxinz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<User> query(Date startTime, Date endTime) {
        Date start = null;
        Date end = null;
        if (startTime !=null)
            start = DateTool.getStartDate(startTime);
        if(endTime !=null)
            end = DateTool.getEndDate(endTime);
        return userMapper.queryByTime(start,end);
    }

    @Override
    public Page<User> query(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return userMapper.query();
    }
}
