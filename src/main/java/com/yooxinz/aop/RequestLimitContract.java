package com.yooxinz.aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@Aspect
@Component
public class RequestLimitContract {

    private static Map<String,Integer> requestLimitMap = new HashMap<>();

    private final static Integer MAX_COUNT = 3;

    private final static Integer MAX_TIME = 20000;

    @Before("execution(* com.yooxinz.controller..*.*(..))")
    public void requestLimit(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String ip = request.getLocalAddr();
        String url = request.getRequestURL().toString();
        String key = "req_limit_".concat(url).concat(ip);

        if(!requestLimitMap.containsKey(key)){
            requestLimitMap.put(key,1);
        }else {
            requestLimitMap.put(key,requestLimitMap.get(key)+1);
        }

        int count = requestLimitMap.get(key);
        if (count > 0) {
            Timer timer= new Timer();
            /**
             * 创建一个新的计时器任务。
             */
            TimerTask task  = new TimerTask(){
                @Override
                public void run() {
                    requestLimitMap.remove(key);
                }
            };
            timer.schedule(task, MAX_TIME);
        }
        if (count > MAX_COUNT) {
            throw new RuntimeException("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + MAX_COUNT + "]");
        }
    }

}
