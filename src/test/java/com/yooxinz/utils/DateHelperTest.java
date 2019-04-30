package com.yooxinz.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Desc：
 * @author: yooxinz
 * @date: 2019-04-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DateHelperTest {

    @Test
    public void getFirstdayOfYearTest(){
        Date firstDay = DateHelper.getFirstDayOfYear(new Date());
        log.info("firstDay==>{}",firstDay);
    }

    @Test
    public void formatDateTimeStrTest(){
        DateHelper.formatDateTimeStr("2011-01-03","yyyy-MM-dd");
    }
}
