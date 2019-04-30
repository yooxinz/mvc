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

/**
 * Created by star on 2018/9/23.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Page<User> query(Date startTime, Date endTime, int pageNum, int pageSize) {
        Date start = null;
        Date end = null;
        if (startTime !=null)
            start = DateTool.getStartDate(startTime);
        if(endTime !=null)
            end = DateTool.getEndDate(endTime);
        PageHelper.startPage(pageNum,pageSize);
        return userMapper.queryByTime(start,end);
    }
}
