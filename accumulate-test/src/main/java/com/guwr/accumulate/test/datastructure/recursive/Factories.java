package com.guwr.accumulate.test.datastructure.recursive;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.recursive.Factories
 * Date         2017/2/21
 * Time         9:49
 * Description  阶层
 */
public class Factories {

    public static void main(String[] args) {
        Factories factories = new Factories();
        int factories1 = factories.factories(100);

        System.out.println("factories1 = " + factories1);
    }

    private int factories(int data) {
        if (data == 1) {
            return 1;
        } else {
            return data + factories(data - 1);
        }
    }
}
