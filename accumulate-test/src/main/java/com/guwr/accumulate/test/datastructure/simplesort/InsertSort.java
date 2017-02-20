package com.guwr.accumulate.test.datastructure.simplesort;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.simplesort.BubbleSort
 * Date         2017/2/15
 * Time         11:42
 * Description  插入法排序
 */
public class InsertSort {

    public static void main(String[] args) {
        InsertSort t = new InsertSort();

        int[] as = new int[]{3, 2, 8, 6, 4, 1};

        t.insertSort(as);
        t.printDatas(as);
    }

    private void insertSort(int[] as) {
        int temp;

        for (int i = 1; i < as.length; i++) {
            temp = as[i];
            int j = 0;
            for (j = i; j > 0; j--) {
                if (as[j - 1] > temp) {
                    as[j] = as[j - 1];
                }else {
                    break;
                }
            }
            as[j] = temp;
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
