package com.guwr.accumulate.test.datastructure.simplesort;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.simplesort.BubbleSort
 * Date         2017/2/15
 * Time         11:42
 * Description  冒泡排序
 */
public class SelectSort {

    public static void main(String[] args) {
        SelectSort t = new SelectSort();

        int[] as = new int[]{3, 2, 8, 6};

        t.selectSort(as);
        t.printDatas(as);
    }

    private void selectSort(int[] as) {
        /**
         *  3, 2, 8, 6, 4, 1
         *  1, 3, 2, 8, 6, 4
         *  1, 2, 3, 8, 6, 4
         *  1, 2, 3, 4, 8, 6
         *  1, 2, 3, 4, 6, 8
         */
        int min = 0;
        for (int i = 0; i < as.length - 1; i++) {
            min = i;
            for (int j = as.length - 1; j > i; j--) {
                if (as[min] < as[j]) {
                    min = j;
                }
            }
            swap(as, min, i);
        }
    }

    private void swap(int[] as, int aIndex, int bIndex) {
        int temp = as[aIndex];
        as[aIndex] = as[bIndex];
        as[bIndex] = temp;
    }

    private void printDatas(int[] as) {
        System.out.println("======================>");
        for (int d : as) {
            System.out.println(d);
        }
    }
}
