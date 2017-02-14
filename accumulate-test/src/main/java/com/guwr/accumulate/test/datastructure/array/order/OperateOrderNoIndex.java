package com.guwr.accumulate.test.datastructure.array.order;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.array.order.OperateOrderNoIndex
 * Date         2017/2/14
 * Time         17:40
 * Description  有序数组,存放不重复的值
 */
public class OperateOrderNoIndex {
    private int[] datas;
    private int currentIndex = 0;

    public OperateOrderNoIndex(int length) {
        this.datas = new int[length];
    }

    public static void main(String[] args) {
        OperateOrderNoIndex t = new OperateOrderNoIndex(10);

        t.insert(3);
        t.insert(6);
        t.insert(1);
        t.insert(2);

        t.printDatas();

//        t.remove(1);
//        t.printDatas();
//
//        int ret = t.searchOne(1);
//        System.out.println("ret==" + ret);
    }

    public void printDatas() {
        System.out.println("======================================>");
        for (int d : datas) {
            System.out.println(d);
        }
    }

    private int insert(int data) {
        /**
         *
         */
        if (currentIndex == 0) {
            datas[currentIndex] = data;
        } else {
            int index = -1;
            for (int i = 0; i < currentIndex; i++) {
                if (datas[i] > data) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                for (int i = currentIndex; i > index; i--) {
                    datas[i] = datas[i-1];
                }
                datas[index] = data;
            } else {
                datas[currentIndex] = data;
            }
        }
        currentIndex++;
        return currentIndex - 1;
    }
}
