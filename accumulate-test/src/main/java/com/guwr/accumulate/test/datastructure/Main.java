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
//        brinySeach();
        sort();
    }

    private static int brinySeach() {
        int index = -1;
        int[] as = new int[]{1, 2, 3, 4, 5, 6};
        int data = 2;
        int maxIndex = as.length - 1;
        int minIndex = 0;
        while (true) {
            int midIndex = (maxIndex + minIndex) / 2;
            if (as[midIndex] == data) {
                return maxIndex;
            }

            if (minIndex >= maxIndex) {
                return -1;
            }
            if (as[midIndex] > data) {
                maxIndex = midIndex - 1;
            } else {
                minIndex = midIndex + 1;
            }
        }
    }

    private static void sort() {
        int[] as = new int[]{3, 2, 8, 6, 4, 1};

        for (int i = 0; i < as.length; i++) {
            for (int j = as.length - 1; j > i; j--) {
                if (as[j] < as[i]) {
                    int temp = as[j];
                    as[j] = as[i];
                    as[i] = temp;
                }
            }
        }

        as = new int[]{3, 2, 8, 6, 4, 1};

        for (int i = 0; i < as.length; i++) {
            int temp = i;
            int j;
            for (j = as.length - 1; j > i; j--) {
                if (as[j] < as[temp]) {
                    temp = j;
                }
            }
            int v = as[i];
            as[i] = as[temp];
            as[temp] = v;
        }

        as = new int[]{3, 2, 8, 6, 4, 1};

        for (int i = 1; i < as.length; i++) {
            int temp = i;
            int j;
            for (j = as.length - 1; j > i; j--) {
                if (as[j] < as[temp]) {
                    temp = j;
                }
            }
            int v = as[i];
            as[i] = as[temp];
            as[temp] = v;
        }
        as = new int[]{3, 2, 8, 6, 4, 1};
        //3, 2, 8, 6, 4, 1
        //2
        for (int i = 1; i < as.length; i++) {
            int temp = as[i];
            int j;
            for (j = i; j > 0; j--) {
                if (as[j - 1] > temp) {
                    as[j] = as[j - 1];
                } else break;
            }
            as[j] = temp;
        }

        as = new int[]{1, 2, 4, 6, 7, 8, 0};

        int k = 5;
        int i;
        for (i = as.length - 2; i > 0; i--) {
            if (k < as[i]) {
                 as[i + 1] = as[i];
            }else break;

        }
        as[i + 1] = k;
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
