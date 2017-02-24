package com.guwr.accumulate.test.datastructure.recursive;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.recursive.Fabonacci
 * Date         2017/2/21
 * Time         14:48
 * Description  斐波那契数列
 */
public class Fabonacci {
    public static void main(String[] args) {
        // 0,1,1,2,3,5,8,13,21
        Fabonacci fabonacci = new Fabonacci();
        int fabonacci1 = fabonacci.fabonacci(7);

        System.out.println("fabonacci1 = " + fabonacci1);
        fabonacci1 = fabonacci.fabonacci1(7);

        System.out.println("fabonacci1 = " + fabonacci1);
    }

    public int fabonacci(int data) {
        if (data == 1) {
            return 0;
        } else if (data == 2) {
            return 1;
        }
        return fabonacci(data - 1) + fabonacci(data - 2);
    }
    public int fabonacci1(int data) {
        if (data == 1) {
            return 0;
        }
        return fabonacci(data) + fabonacci1(data - 1);
    }
}
