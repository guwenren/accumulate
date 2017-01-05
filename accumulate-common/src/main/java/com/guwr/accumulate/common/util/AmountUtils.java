package com.guwr.accumulate.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static Logger logger = LoggerFactory.getLogger(AmountUtils.class);

    private AmountUtils() {

    }

    /**
     * 四舍五入
     *
     * @param v 需要四舍五入的数字
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

    /**
     * 计算预期收益
     *
     * @param interestrate  vip利率
     * @param pInterestrate 产品利率
     * @param phases        期限
     * @param invest        投资总额
     * @return
     */
    public static BigDecimal calculateProearn(BigDecimal interestrate, BigDecimal pInterestrate, Integer phases, BigDecimal invest) {
        logger.info("interestrate = [" + interestrate + "], pInterestrate = [" + pInterestrate + "], phases = [" + phases + "], invest = [" + invest + "]");
        BigDecimal proearn;
        BigDecimal realInterestrate = interestrate.add(pInterestrate); // 真实利率 =  vip利率 + 产品利率
        BigDecimal multiply = invest.multiply(realInterestrate);// 真实利率 * 投资有效金额
        BigDecimal divAmount = AmountUtils.div(multiply, new BigDecimal(365));// 每天收益
//        BigDecimal divAmount = multiply.divide(new BigDecimal(365),ROUND_HALF_DOWN);// 每天收益
        proearn = divAmount.multiply(new BigDecimal(phases)); // 预期收益
        return proearn;
    }

    public static void main(String[] args) {
//        BigDecimal b1 = new BigDecimal("369.8670136986301");
//        BigDecimal round = round(b1, 2);
//        System.out.println("round = " + round);

        //{"createTime":1482896156000,"id":2,"interestrate":0.0010,"level":1,"maxInvest":200000,"minInvest":100000,"updateTime":1482896156000,"uuid":"063f4f291df94e7594f987a914e06d3d","version":0}

        BigDecimal bigDecimal = calculateProearn(new BigDecimal(0.0010), new BigDecimal(0.0073), 3, new BigDecimal(200000));

        System.out.println("bigDecimal = " + bigDecimal);
    }
}
