package com.guwr.accumulate.test.datastructure.array.order;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.array.order.OperateOrderNoIndex
 * Date         2017/2/14
 * Time         17:40
 * Description  二分法查找
 */
public class OperateOrderNoIndexBinarySearch {
    private int[] datas;
    private int currentIndex = 0;

    private OperateOrderNoIndexBinarySearch(int length) {
        this.datas = new int[length];
    }

    public static void main(String[] args) {
        OperateOrderNoIndexBinarySearch t = new OperateOrderNoIndexBinarySearch(10);
//
//        t.insert(3);
//        t.insert(6);
//        t.insert(1);
//        t.insert(2);
//
//        t.printDatas();
//
//        t.remove(6);
//        t.printDatas();
//
//        int ret = t.searchOne(6);;
//        System.out.println("ret==" + ret);


        int[] datas = {1,4,6,7,8};
        int data = 7;
        int i = t.binarySearch1(datas, data, 0, datas.length - 1);
        System.out.println(i);
    }

    public int insert(int data) {
        //假设顺序是升序
        int index = 0;
        //1：查找数据data应该存放的位置
        for (index = 0; index < currentIndex; index++) {
            if (datas[index] > data) {
                break;
            }
        }
        //2：把这个位置及其后面的数据，向后移动一位
        for (int i = currentIndex; i > index; i--) {
            datas[i] = datas[i - 1];
        }
        //3：把data设置到应该存放的位置
        datas[index] = data;

        currentIndex++;
        return currentIndex - 1;
    }

    private int binarySearch(int data) {
        int index = -1;
        int lowIndex = 0;
        int highIndex = currentIndex - 1;
        while (true) {
            index = (lowIndex + highIndex) / 2;
            if (datas[index] == data) {
                return index;
            } else {
                if (datas[index] < data) {
                    lowIndex = index + 1;
                } else {
                    highIndex = index - 1;
                }
                if (lowIndex > highIndex) {
                    System.out.println("没有找到数据");
                    return -1;
                }
            }
        }
    }

    private int binarySearch1(int[] datas, int data, int sIndex, int eIndex) {
        int index;
        index = (sIndex + eIndex) / 2;
        if (sIndex > eIndex) {
            return -1;
        } else if (datas[index] == data) {
            return index;
        } else {
            if (datas[index] > data) {
                eIndex = index - 1;
            } else {
                sIndex = index + 1;

            }
            return binarySearch1(datas,data,sIndex,eIndex);
        }
    }

    public void remove(int data) {
        //1：查找这个数据对应的索引
        int index = this.binarySearch(data);
        //2：同前一个演示
        for (int i = index; i < currentIndex; i++) {
            datas[i] = datas[i + 1];
        }
        currentIndex--;
    }

    public int searchOne(int data) {
        //1：查找这个数据对应的索引
        int index = this.binarySearch(data);
        //2：如果有，就返回datas中的数据
        if (index >= 0) {
            return datas[index];
        }
        //3：如果没有，就返回0；
        return 0;
    }

    private void sort() {

    }

    public void printDatas() {
        System.out.println("======================================>");
        for (int d : datas) {
            System.out.println(d);
        }
    }
}
