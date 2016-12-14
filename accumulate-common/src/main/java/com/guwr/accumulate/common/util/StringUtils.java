package com.guwr.accumulate.common.util;

import com.alibaba.fastjson.JSON;

import java.util.*;

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
//        String uuid = getUUID();
//        System.out.println("uuid = " + uuid);
//
//        BigDecimal b1 = new BigDecimal(4000);
//        BigDecimal b2 = new BigDecimal(99);
//
//        //一个倍数一个余数
//        BigDecimal[] bigDecimals = b1.divideAndRemainder(b2);
//
//        System.out.println("bigDecimals = " + JSON.toJSONString(bigDecimals));
//
//        double floor = Math.floor(501 / 500);
//
//        System.out.println("floor = " + floor);

        //fbabedde97574f4d938362d822a7868b
        //b4259af28acc4f039a5815164663fac0
        double ceil = Math.ceil(1001 / 1000);

        int c = 42 % 5;


        System.out.println("c = " + c);



        System.out.println("ceil = " + ceil);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 42; i++) {
            list.add(i);
        }

        List<Integer> list0 = new ArrayList<>();
//        List<Integer> list1 = new ArrayList<>();
//        List<Integer> list2 = new ArrayList<>();

        int count = 5;
        Map<Integer,List<Integer>> mapList = new HashMap<>();
        for (int i = 0; i < count; i++) {
            mapList.put(i, new ArrayList<Integer>());
        }

        for (Integer integer : list) {
//            if (integer % 3 == 0) {
//                list0.add(integer);
//            } else if (integer % 3 == 1) {
//                list1.add(integer);
//            } else if (integer % 3 == 2) {
//                list2.add(integer);
//            }
            for (int i = 0; i < count; i++) {
                if (integer % count == i) {
                    mapList.get(i).add(integer);
                    break;
                }
            }
        }

        System.out.println("JSON.toJSONString(list) = " + JSON.toJSONString(list) + ",count = " + list.size());
        System.out.println("JSON.toJSONString(list0) = " + JSON.toJSONString(mapList) + ",count = " + mapList.size());
//        System.out.println("JSON.toJSONString(list1) = " + JSON.toJSONString(list1) + ",count = " + list1.size());
//        System.out.println("JSON.toJSONString(list2) = " + JSON.toJSONString(list2) + ",count = " + list2.size());
    }
}
