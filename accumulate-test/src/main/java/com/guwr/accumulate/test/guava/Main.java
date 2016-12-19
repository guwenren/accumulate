package com.guwr.accumulate.test.guava;

import com.google.common.base.Objects;
import com.google.common.base.Optional;

import java.util.Date;

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

        Date date = null;
        Optional<Date> of = Optional.of(date);

        System.out.println("of = " + of);
        System.out.println("Main.main");
    }
}
