package com.yooxinz.common;

import java.util.Calendar;
import java.util.Date;

public class DateTool {

//    获取一天总最开始的时间 eg:2018/12/12 00:00:00
    public static Date getStartDate(Date date){
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);
        return startDate.getTime();
    }

//    获取一天中最晚的时间 eg 2018/12/12 23:59:59
    public static Date getEndDate(Date date){
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(date);
        endDate.set(Calendar.HOUR_OF_DAY, 23);
        endDate.set(Calendar.MINUTE, 59);
        endDate.set(Calendar.SECOND, 59);
        endDate.set(Calendar.MILLISECOND, 999);
        return endDate.getTime();
    }
}
