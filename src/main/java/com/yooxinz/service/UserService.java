package com.yooxinz.service;


import com.yooxinz.dto.User;

import java.util.Date;
import java.util.List;

/**
 * Created by star on 2018/9/23.
 */
public interface UserService {

    User getUserNameById(Long userId);

    List<User> query();

    List<User> query(Date startTime, Date endTime);
}
