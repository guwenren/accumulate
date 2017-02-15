package com.guwr.accumulate.test.datastructure.simplesort;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.simplesort.BubbleSort
 * Date         2017/2/15
 * Time         11:42
 * Description  冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        BubbleSort t = new BubbleSort();
        int[] as = new int[]{3, 2, 8, 6};
        t.bubbleSort2(as);
        t.printDatas(as);
    }

    private void printDatas(int[] as) {
        System.out.println("======================>");
        for (int d : as) {
            System.out.println(d);
        }
    }

    /**
     * @param as
     */
    private void bubbleSort(int[] as) {
        /**
         *  3,2,8,6
         *  2,3,8,6
         *  2,3,6,8
         */
        for (int i = 0; i < as.length - 1; i++) {
            System.out.println(as[i]);
            for (int j = as.length - 1; j > i; j--) {
                if (as[i] < as[j]) {
                    swap(as, i, j);
                }
            }
        }
    }

    /**
     * @param as
     */
    private void bubbleSort2(int[] as) {
        /**
         *  3,2,8,6
         *  2,3,8,6
         *  2,3,6,8
         */
        for (int i = 0; i < as.length - 1; i++) {
            System.out.println(as[i]);
            for (int j = i + 1; j < as.length; j++) {
                if (as[i] < as[j]) {
                    swap(as, i, j);
                }
            }
        }
    }

    private void swap(int[] as, int aIndex, int bIndex) {
        int temp = as[aIndex];
        as[aIndex] = as[bIndex];
        as[bIndex] = temp;
    }
}
