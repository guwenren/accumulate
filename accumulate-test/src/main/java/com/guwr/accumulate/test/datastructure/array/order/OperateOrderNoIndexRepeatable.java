package com.guwr.accumulate.test.datastructure.array.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.array.order.OperateOrderNoIndex
 * Date         2017/2/14
 * Time         17:40
 * Description  有序数组,存放重复的值
 */
public class OperateOrderNoIndexRepeatable {
    private int[] datas;
    private int currentIndex = 0;

    private OperateOrderNoIndexRepeatable(int length) {
        this.datas = new int[length];
    }

    public static void main(String[] args) {
        OperateOrderNoIndexRepeatable t = new OperateOrderNoIndexRepeatable(10);

        t.insert(3);
        t.insert(6);
        t.insert(1);
        t.insert(2);
        t.insert(2);
        t.insert(6);

        t.printDatas();

        t.remove(6);
        t.printDatas();

        List<Integer> ret = t.searchOne(2);
        System.out.println("ret==" + ret);
    }

    private List<Integer> searchOne(int data) {
        List<Integer> integerList = new ArrayList<>();
        //1：查找这个数据对应的索引
        int index = this.getIndex(0, data);
        while (index != -1) {
            integerList.add(datas[index]);
            index = this.getIndex(index+1, data);
        }
        //3：如果没有，就返回0；
        return integerList;
    }

    private void remove(int data) {
        //1：查找这个数据对应的索引
        int index = this.getIndex(0, data);
        while (index != -1) {
            for (int i = index; i < currentIndex; i++) {
                datas[i] = datas[i + 1];
            }
            currentIndex--;
            index = this.getIndex(index, data);
        }
    }

    private void printDatas() {
        System.out.println("======================================>");
        for (int d : datas) {
            System.out.println(d);
        }
    }

    private int insert(int data) {
        /**
         *
         */
//        if (currentIndex == 0) {
//            datas[currentIndex] = data;
//        } else {
//            int index = -1;
//            for (int i = 0; i < currentIndex; i++) {
//                if (datas[i] > data) {
//                    index = i;
//                    break;
//                }
//            }
//            if (index != -1) {
//                //索引向后移动一位
//                for (int i = currentIndex; i > index; i--) {
//                    datas[i] = datas[i - 1];
//                }
//                datas[index] = data;
//            } else {
//                datas[currentIndex] = data;
//            }
//        }

        int index;
        for (index = 0; index < currentIndex; index++) {
            if (datas[index] > data) {
                break;
            }
        }

        for (int i = currentIndex; i > index; i--) {
            datas[i] = datas[i - 1];
        }

        datas[index] = data;
        currentIndex++;
        return currentIndex - 1;
    }

    private int getIndex(int begin, int data) {
        int index = -1;
        for (int i = begin; i < currentIndex; i++) {
            if (Objects.equals(data, datas[i])) {
                index = i;
                break;
            }
        }
        return index;
    }
}
