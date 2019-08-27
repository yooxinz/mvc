package com.yooxinz.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Desc：
 * @author: yooxinz
 * @date: 2019-08-27
 */
@Service
@Slf4j
public class HelloWorldJob {
    //cron = "0/3 40 11 * * ?" 每天11:40触发，没三秒执行一次
    @Scheduled(cron = "1-2 * * * * ? ")
    public void printTime() {
        log.info("Hello World！");
    }
}
