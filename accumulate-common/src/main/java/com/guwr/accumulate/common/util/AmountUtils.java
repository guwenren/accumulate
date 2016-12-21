package com.guwr.accumulate.common.util;

import java.math.BigDecimal;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.common.util.AmountUtils
 * Date         2016/11/22
 * Time         17:13
 * Description  资金Utils
 */
public class AmountUtils {
    private AmountUtils() {

    }

    /**
     * 四舍五入
     *
     * @param v     需要四舍五入的数字
     * @return
     */
    public static BigDecimal round(BigDecimal v) {
        return v.divide(BigDecimal.ONE, 2, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 四舍五入
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return
     */
    public static BigDecimal round(BigDecimal v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return v.divide(BigDecimal.ONE, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法运算，当发生除不尽的情况时，精确到小数点以后2位，以后的数字四舍五入
     *
     * @param v1 被除数
     * @param v2 除数
     * @return
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2) {
        return div(v1, v2, 2);
    }

    /**
     * 除法运算，当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 精确到小数点以后几位
     * @return
     */
    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return v1.divide(v2, scale, BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal("369.8670136986301");
        BigDecimal round = round(b1, 2);
        System.out.println("round = " + round);
    }
}
