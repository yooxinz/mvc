package com.yooxinz.user;

import com.yooxinz.dto.User;
import com.yooxinz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Desc：
 * @author: yooxinz
 * @date: 2019-04-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserControllerTest {

    @Autowired
    UserService userSerivce;

    @Test
    public void getUser(){
        User user = userSerivce.getUserNameById((long)1);
        log.info("result ==>{}",user);

    }
}
