package com.yooxinz.rabbitmq;

import com.yooxinz.controller.RabbitMqController;
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
public class RabbitMqControllerTest {

    @Autowired
    RabbitMqController rabbitMqController;

    @Test
    public void sendMessage(){
        rabbitMqController.driectSend("你是个好人2");
    }
}
