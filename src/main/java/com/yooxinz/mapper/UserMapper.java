package com.yooxinz.mapper;

import com.github.pagehelper.Page;
import com.yooxinz.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * Created by star on 2018/9/23.
 */
@Mapper
public interface UserMapper {

    User getUserNameById(Long userId);

    Page<User> query();

    List<User> queryByTime(Date startTime, Date endTime);

}
