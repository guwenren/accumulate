package com.guwr.accumulate.common.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.common.util.DateUtils
 * Date 2016/8/28
 * Time 23:48
 */
public class DateUtils implements Serializable {

    private DateUtils() {
    }

    /**
     * 当前Seconds
     *
     * @return
     */
    public static int currentTimeSeconds() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 计算 day 天后的时间
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }
}
