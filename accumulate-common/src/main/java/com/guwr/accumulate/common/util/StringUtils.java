package com.guwr.accumulate.common.util;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.common.util.StringUtils
 * Date         2016/11/6
 * Time         10:52
 * Description
 */
public class StringUtils {
    /**
     * 获取UUID
     *
     * @return UUID
     */
    public static String getUUID() {
        return (UUID.randomUUID() + "").replaceAll("-", "");
    }

    public static void main(String[] args) {
        String uuid = getUUID();
        System.out.println("uuid = " + uuid);

        BigDecimal b1 = new BigDecimal(4000);
        BigDecimal b2 = new BigDecimal(99);

        //一个倍数一个余数
        BigDecimal[] bigDecimals = b1.divideAndRemainder(b2);

        System.out.println("bigDecimals = " + JSON.toJSONString(bigDecimals));

        double floor = Math.floor(501/ 500);

        System.out.println("floor = " + floor);

        //fbabedde97574f4d938362d822a7868b
        //b4259af28acc4f039a5815164663fac0
    }
}
