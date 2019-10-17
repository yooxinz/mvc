package com.yooxinz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by star on 2018/8/17.
 */
@Component
@Slf4j
public class RabbitMqController {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void driectSend(String message) {
        System.out.println("Direct 发送消息：" + message);
        //参数一：交换器名称，可以省略（省略存储到AMQP default交换器）；参数二：路由键名称（direct模式下路由键=队列名称）；参数三：存储消息
        this.rabbitTemplate.convertAndSend("direct", message);
    }
}
