package com.yooxinz.service;


import com.github.pagehelper.Page;
import com.yooxinz.dto.User;

import java.util.Date;

/**
 * Created by star on 2018/9/23.
 */
public interface UserService {

    Page<User> query(Date startTime, Date endTime,int pageNum, int pageSize);

}
