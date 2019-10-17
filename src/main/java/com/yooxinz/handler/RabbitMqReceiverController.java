package com.yooxinz.handler;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by star on 2018/8/17.
 */
@Component
@RabbitListener(queues = "direct")
public class RabbitMqReceiverController {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RabbitHandler
    /**
     * 监听消费消息
     */
    public void process(String message) {
        System.out.println("Direct 消费消息：" + message);
    }
}
