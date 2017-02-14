package com.guwr.accumulate.test.guava;

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

        List<String> stringList = new ArrayList<>();

//        stringList.replaceAll();


        stringList.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s = " + s);
            }
        });

        Date date = null;
        Optional<Date> of = Optional.of(date);

        System.out.println("of = " + of);
        System.out.println("Main.main");
    }
}
