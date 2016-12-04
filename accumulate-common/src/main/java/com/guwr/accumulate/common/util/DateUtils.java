package com.guwr.accumulate.common.util;

import java.io.Serializable;

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
}
