package com.guwr.accumulate.test.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.guava.Main
 * Date         2016/12/19
 * Time         11:36
 * Description
 */
public class Main {
    public static void main(String[] args) {

//        List<String> stringList = new ArrayList<>();
//
////        stringList.replaceAll();
//
//
//        stringList.forEach(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println("s = " + s);
//            }
//        });
//
//        Date date = null;
//        Optional<Date> of = Optional.of(date);
//
//        System.out.println("of = " + of);
//        System.out.println("Main.main");

        int[] uids = {2314, 1231, 1234, 2342, 56712, 235423, 8432, 3456, 7890,5};

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> arrayList2 = new ArrayList<>();
        List<Integer> arrayList3 = new ArrayList<>();
        List<Integer> arrayList4 = new ArrayList<>();
        for (int uid : uids) {
            if (uid % 2 == 0) {
                if(uid % 4 == 0){
                    arrayList.add(uid);
                }else {
                    arrayList2.add(uid);
                }

//                System.out.println("uid = 0," + uid);
            } else {
                if(uid % 4 == 1){
                    arrayList3.add(uid);
                }else {
                    arrayList4.add(uid);
                }
//                System.out.println("uid = 1," + uid);
            }
        }
        System.out.println("JSON.toJSONString(arrayList = " + JSON.toJSONString(arrayList));
        System.out.println("JSON.toJSONString(arrayList2 = " + JSON.toJSONString(arrayList2));
        System.out.println("JSON.toJSONString(arrayList3 = " + JSON.toJSONString(arrayList3));
        System.out.println("JSON.toJSONString(arrayList4 = " + JSON.toJSONString(arrayList4));
    }
}
//JSON.toJSONString(arrayList = [2314,1234,2342,56712,8432,3456,7890]
//JSON.toJSONString(arrayList2 = [1231,235423]