package com.yooxinz.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Joiner;
import com.yooxinz.dto.User;
import com.yooxinz.dto.UserImportDto;
import com.yooxinz.enums.RegexEnum;
import com.yooxinz.enums.UseStateEnum;
import com.yooxinz.mapper.UserMapper;
import com.yooxinz.service.UserService;
import com.yooxinz.utils.CollectionHelper;
import com.yooxinz.utils.DateHelper;
import com.yooxinz.utils.ObjectHelper;
import com.yooxinz.utils.RegexHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

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
            start = DateHelper.getFirstTimeOfDay(startTime);
        if(endTime !=null)
            end = DateHelper.getLastTimeOfDay(endTime);
        PageHelper.startPage(pageNum,pageSize);
        return userMapper.queryByTime(start,end);
    }

    @Override
    public String importUser(InputStream inputStream) {
        Date createDate = new Date();
        Integer success = 0;
        // 从流对象获取得到excel数据
        List<UserImportDto> list = checkList(inputStream);
        //规则校验
        Set<Integer> failSet = checkArg(list);

        // 持久化到数据库
        if (!CollectionHelper.isEmpty(list)) {
            List<User> userList = new ArrayList<>();
            list.forEach(t -> {
                if(!failSet.contains(list.indexOf(t) + 2)) {
                    User user = new User();
                    // 构建一个simInfo实体对象
                    BeanUtils.copyProperties(t, user);
                    user.setBirthday(DateHelper.formatDateTimeStr(t.getBirthday(),"yyyy/MM/dd"));
                    user.setState(UseStateEnum.ENABLE.isValue());
                    user.setCreateDate(createDate);
                    //设置默认值
                    userList.add(user);
                }
            });
            if(userList.size() != 0){
                success = userMapper.addBatch(userList);
            }else {
                success = 0;
            }
        }
        return "本次导入成功"+ success +"条，失败"+ failSet.size() +"条，失败行数:" + Joiner.on(",").join(failSet);
    }

    private List<UserImportDto> checkList(InputStream inputStream) {
        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(1, 1, UserImportDto.class));
        List<UserImportDto> newList = new ArrayList<>(data.size());
        data.forEach(item -> {
            if(ObjectHelper.isEmpty(item)){
                return;
            }
            newList.add((UserImportDto) item);
        });
        return newList;
    }

    private Set<Integer> checkArg(List<UserImportDto> list ){
        Set<Integer> set = new HashSet<>();
        list.forEach(t -> {
            int lineNum = list.indexOf(t) + 2;
            // 必填性校验
            if (org.apache.commons.lang3.StringUtils.isAnyEmpty(t.getName(), t.getBirthday())) {
                set.add(lineNum);
            }

            if(set.size()==0 || !set.contains(lineNum)){
                //姓名校验
                if(!RegexHelper.match(t.getName(), RegexEnum.NAME)){
                    set.add(lineNum);
                }
                //校验生日
                if(!RegexHelper.match(t.getBirthday(), RegexEnum.DATE)){
                    set.add(lineNum);
                }
            }
        });
        return set;
    }
}
