package com.guwr.accumulate.test.datastructure;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.Main
 * Date         2017/2/16
 * Time         11:00
 * Description
 */
public class Main {
    public static void main(String[] args) {


    }

    private static void sort() {
        int[] as = new int[]{3, 2, 8, 6, 4, 1};
        int temp;
        for (int i = 0; i < as.length; i++) {
            temp = i;
            for (int j = as.length - 1; j > i; j--) {
                if (as[j] < as[temp]) {
                    temp = j;
                }
            }
            int v = as[temp];
            as[temp] = as[i];
            as[i] = v;
        }
        printDatas(as);

        as = new int[]{3, 2, 8, 6, 4, 1};

        for (int i = 1; i < as.length; i++) {
            temp = as[i];
            int j;
            for (j = i; j > 0; j--) {
                if (as[j - 1] > temp) {
                    as[j] = as[j - 1];
                } else {
                    break;
                }
            }
            as[j] = temp;
        }
        printDatas(as);

        as = new int[]{3, 2, 8, 6, 4, 1};

        for (int i = 0; i < as.length; i++) {
            for (int j = as.length - 1; j > i; j--) {
                if (as[j] < as[i]) {
                    int v = as[j];
                    as[j] = as[i];
                    as[i] = v;
                }
            }
        }
        printDatas(as);
    }

    private static void swap(int[] as, int aIndex, int bIndex) {
        int temp = as[aIndex];
        as[aIndex] = as[bIndex];
        as[bIndex] = temp;
    }

    private static void printDatas(int[] as) {
        System.out.println("======================>");
        for (int d : as) {
            System.out.println(d);
        }
    }
}
