package com.yooxinz.mapper;

import com.github.pagehelper.Page;
import com.yooxinz.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * Created by star on 2018/9/23.
 */
@Mapper
public interface UserMapper {

    Page<User> queryByTime(Date startTime, Date endTime);

}
