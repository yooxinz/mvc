package com.yooxinz.mapper;

import com.yooxinz.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by star on 2018/9/23.
 */
@Mapper
public interface UserMapper {

    User getUserNameById(Long userId);

    List<User> query();

}
