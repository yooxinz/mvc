package com.yooxinz.utils;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Desc：
 * @author: yooxinz
 * @date: 2019-04-30
 */
public class DateHelper {

    public static String YEARMONTHDAY = "yyyy-MM-dd";

    private static String YEARMONTH = "yyyy-MM";

    public static String formatDateLongToStr(Long time){
        if (time == null){
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(YEARMONTHDAY);
        return df.format(new Date(time));
    }

    public static String dateToDay(){
        SimpleDateFormat df = new SimpleDateFormat(YEARMONTHDAY);
        return df.format(new Date());
    }

    public static String dateToMonth(){
        SimpleDateFormat df = new SimpleDateFormat(YEARMONTH);
        return df.format(new Date());
    }


    public static Date formatDateTimeStr(String dateTimeStr, String pattern) {
        Date date = null;
        if (StringUtils.isEmpty(dateTimeStr)) {
            date = new Date();
        } else {
            try {
                SimpleDateFormat format;
                if (StringUtils.isEmpty(pattern)) {
                    format = new SimpleDateFormat("yyyyMMddHHmmss");
                } else {
                    format = new SimpleDateFormat(pattern);
                }

                date = format.parse(dateTimeStr);
            } catch (ParseException var4) {
                var4.printStackTrace();
            }
        }

        return date;
    }

    public static String alipayDateParse(String aliDateStr) {
        String resultDataStr;
        if (!StringUtils.isEmpty(aliDateStr)) {
            Date aliDate = formatDateTimeStr(aliDateStr, "yyyy-MM-dd HH:mm:ss");
            resultDataStr = formatDate(aliDate, "yyyyMMddHHmmss");
        } else {
            resultDataStr = formatDate(new Date(), "yyyyMMddHHmmss");
        }

        return resultDataStr;
    }

    public static String formatDate(Date date, String pattern) {
        if (null == date) {
            return null;
        } else {
            SimpleDateFormat format;
            if (StringUtils.isEmpty(pattern)) {
                format = new SimpleDateFormat("yyyyMMddHHmmss");
            } else {
                format = new SimpleDateFormat(pattern);
            }

            return format.format(date);
        }
    }

    public static Date addYear(Date date, int n) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        rightNow.add(1, n);
        return rightNow.getTime();
    }

    public static Date addMonth(Date date, int n) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        rightNow.add(2, n);
        return rightNow.getTime();
    }

    public static Date addWeek(Date date, int n) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        rightNow.add(3, n);
        return rightNow.getTime();
    }

    public static Date addDay(Date date, int n) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        rightNow.add(6, n);
        return rightNow.getTime();
    }

    public static Date addHour(Date date, int n) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        rightNow.add(11, n);
        return rightNow.getTime();
    }

    public static Date addMinute(Date date, int n) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        rightNow.add(12, n);
        return rightNow.getTime();
    }

    public static Date addSeconds(Date date, int n) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        rightNow.add(13, n);
        rightNow.set(14, 0);
        return rightNow.getTime();
    }

    public static int getYear(Date date) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        return rightNow.get(1);
    }

    public static int getYear() {
        return getYear((Date)null);
    }

    public static int getMonthOfYear(Date date) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        return rightNow.get(2);
    }

    public static int getMonthOfYear() {
        return getMonthOfYear((Date)null);
    }

    public static int getQuarterOfYear(Date date) {
        return getMonthOfYear(date) / 3 + 1;
    }

    public static int getQuarterOfYear() {
        return getQuarterOfYear((Date)null);
    }

    public static int getWeekOfYear(Date date) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        int rightWeek = rightNow.get(3);
        if (rightWeek == 1 && rightNow.get(2) == 11) {
            rightWeek = 53;
        }

        return rightWeek;
    }

    public static int getWeekOfYear() {
        return getWeekOfYear((Date)null);
    }

    public static int getWeekDay(Date date) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        return rightNow.get(7);
    }

    public static int getWeekDay() {
        return getWeekDay((Date)null);
    }

    public static int getMonthDay(Date date) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        return rightNow.get(5);
    }

    public static Date getFirstdayOfYear(int year) {
        Calendar rightNow = new GregorianCalendar();
        rightNow.set(1, year);
        rightNow.set(2, 0);
        rightNow.set(5, rightNow.getActualMinimum(5));
        rightNow.set(11, 0);
        rightNow.set(12, 0);
        rightNow.set(13, 0);
        rightNow.set(14, 0);
        return rightNow.getTime();
    }

    public static Date getFirstDayOfYear(Date date){
        Calendar rightNow= new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }
        int currentYear = rightNow.get(Calendar.YEAR);
        return getFirstdayOfYear(currentYear);
    }

    public static Date getLastDayOfYear(int year) {
        Calendar rightNow = new GregorianCalendar();
        rightNow.set(1, year);
        rightNow.set(2, 11);
        rightNow.set(5, rightNow.getActualMaximum(5));
        rightNow.set(11, 23);
        rightNow.set(12, 59);
        rightNow.set(13, 59);
        rightNow.set(14, 999);
        return rightNow.getTime();
    }

    public static Date getLastDayOfYear(Date date){
        Calendar rightNow= new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }
        int currentYear = rightNow.get(Calendar.YEAR);
        return getLastDayOfYear(currentYear);
    }

    public static Date getFirstdayOfMonth(Date date) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        rightNow.set(5, rightNow.getActualMinimum(5));
        rightNow.set(11, 0);
        rightNow.set(12, 0);
        rightNow.set(13, 0);
        rightNow.set(14, 0);
        return rightNow.getTime();
    }

    public static Date getFirstdayOfMonth(int n) {
        Calendar rightNow = new GregorianCalendar();
        rightNow.set(2, n);
        rightNow.set(5, rightNow.getActualMinimum(5));
        rightNow.set(11, 0);
        rightNow.set(12, 0);
        rightNow.set(13, 0);
        rightNow.set(14, 0);
        return rightNow.getTime();
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        rightNow.set(5, rightNow.getActualMaximum(5));
        rightNow.set(11, 23);
        rightNow.set(12, 59);
        rightNow.set(13, 59);
        rightNow.set(14, 999);
        return rightNow.getTime();
    }

    public static Date getLastDayOfMonth(int n) {
        Calendar rightNow = new GregorianCalendar();
        rightNow.set(2, n);
        rightNow.set(5, rightNow.getActualMaximum(5));
        rightNow.set(11, 23);
        rightNow.set(12, 59);
        rightNow.set(13, 59);
        rightNow.set(14, 999);
        return rightNow.getTime();
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        rightNow.set(7, 1);
        rightNow.set(11, 0);
        rightNow.set(12, 0);
        rightNow.set(13, 0);
        rightNow.set(14, 0);
        return rightNow.getTime();
    }

    public static Date getFirstDayOfWeek(int n) {
        Calendar rightNow = new GregorianCalendar();
        rightNow.set(3, n);
        rightNow.set(7, 1);
        rightNow.set(11, 0);
        rightNow.set(12, 0);
        rightNow.set(13, 0);
        rightNow.set(14, 0);
        return rightNow.getTime();
    }

    public static Date getLastDayOfWeek(Date date) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        rightNow.set(7, 7);
        rightNow.set(11, 23);
        rightNow.set(12, 59);
        rightNow.set(13, 59);
        rightNow.set(14, 999);
        return rightNow.getTime();
    }

    public static Date getLastDayOfWeek(int n) {
        Calendar rightNow = new GregorianCalendar();
        rightNow.set(3, n);
        rightNow.set(7, 7);
        rightNow.set(11, 23);
        rightNow.set(12, 59);
        rightNow.set(13, 59);
        rightNow.set(14, 999);
        return rightNow.getTime();
    }

    public static Date getFirstTimeOfDay(Date date) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        rightNow.set(11, 0);
        rightNow.set(12, 0);
        rightNow.set(13, 0);
        rightNow.set(14, 0);
        return rightNow.getTime();
    }

    public static Date getLastTimeOfDay(Date date) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        rightNow.set(11, 23);
        rightNow.set(12, 59);
        rightNow.set(13, 59);
        rightNow.set(14, 999);
        return rightNow.getTime();
    }

    public static Date getDateTimeOfDay(Date date, int hour, int minute, int second) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }

        rightNow.set(11, hour);
        rightNow.set(12, minute);
        rightNow.set(13, second);
        rightNow.set(14, 0);
        return rightNow.getTime();
    }

    public static int compare(Date d1, Date d2) {
        Assert.notNull(d1);
        Assert.notNull(d2);
        Calendar c1 = new GregorianCalendar();
        Calendar c2 = new GregorianCalendar();
        c1.setTime(d1);
        c2.setTime(d2);
        return c1.compareTo(c2);
    }

    public static int compareDate(Date d1, Date d2) {
        Assert.notNull(d1);
        Assert.notNull(d2);
        Calendar c1 = new GregorianCalendar();
        c1.setTime(d1);
        c1.set(11, 0);
        c1.set(12, 0);
        c1.set(13, 0);
        c1.set(14, 0);
        Calendar c2 = new GregorianCalendar();
        c2.setTime(d2);
        c2.set(11, 0);
        c2.set(12, 0);
        c2.set(13, 0);
        c2.set(14, 0);
        return c1.compareTo(c2);
    }

    public static Long getDiffMillis(Date d1, Date d2) {
        Assert.notNull(d1);
        Assert.notNull(d2);
        long diffMillis = d2.getTime() - d1.getTime();
        return Math.abs(diffMillis);
    }

    public static long getDiffDays(Date d1, Date d2) {
        Assert.notNull(d1);
        Assert.notNull(d2);
        Calendar c1 = new GregorianCalendar();
        c1.setTime(d1);
        c1.set(11, 0);
        c1.set(12, 0);
        c1.set(13, 0);
        c1.set(14, 0);
        Calendar c2 = new GregorianCalendar();
        c2.setTime(d2);
        c2.set(11, 0);
        c2.set(12, 0);
        c2.set(13, 0);
        c2.set(14, 0);
        long diffMillis = c2.getTimeInMillis() - c1.getTimeInMillis();
        long diffDays = 0L;
        if (diffMillis != 0L) {
            long dayMills = 86400000L;
            diffDays = diffMillis / dayMills;
        }

        return diffDays;
    }

    public static int getNowMaxMonthDay() {
        GregorianCalendar c = new GregorianCalendar();
        return c.getActualMaximum(5);
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }


       /* public static String formatDateLongToStr(Long time){
            if (time == null){
                return null;
            }
            SimpleDateFormat df = new SimpleDateFormat(YEARMONTHDAY);
            return df.format(new Date(time));
        }
*/

    public static Date getNextDay(Date date) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }
        rightNow.add(rightNow.DATE,+1);
        return rightNow.getTime();
    }

    public static Date getPreviousDay(Date date) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }
        rightNow.add(rightNow.DATE,-1);
        return rightNow.getTime();
    }

    public static Date getPreviousMonth(Date date) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }
        rightNow.add(rightNow.MONTH,-1);
        return rightNow.getTime();
    }

    public static Date getPreviousYear(Date date) {
        Calendar rightNow = new GregorianCalendar();
        if (date != null) {
            rightNow.setTime(date);
        }
        rightNow.add(rightNow.YEAR,-1);
        return rightNow.getTime();
    }
}
