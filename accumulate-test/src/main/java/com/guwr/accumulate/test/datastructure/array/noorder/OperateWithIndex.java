package com.guwr.accumulate.test.datastructure.array.noorder;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.array.noorder.OperateWithIndex
 * Date         2017/2/14
 * Time         16:13
 * Description  根据索引操作数组
 */
public class OperateWithIndex {
    private int[] datas;
    private int currentIndex = 0;

    public OperateWithIndex(int length) {
        this.datas = new int[length];
    }

    public static void main(String[] args) {
        OperateWithIndex t = new OperateWithIndex(20);
        t.insert(3);
        t.insert(6);
        t.insert(1);
        t.insert(2);
        t.printDatas();
        t.remove(1);
        t.printDatas();
        int ret = t.searchOne(2);
        System.out.println("i = " + ret);
    }

    private int insert(int data) {
        datas[currentIndex] = data;
        currentIndex++;
        return currentIndex - 1;
    }

    private void remove(int index) {
        // 从需要删除的索引开始，将后面的元素一个个向前移一位
        for (int i = index; i < currentIndex; i++) {
            datas[i] = datas[i + 1];
        }
        currentIndex--;
    }

    public int searchOne(int index) {
        return datas[index];
    }

    public void printDatas() {
        System.out.println("======================================>");
        for (int d : datas) {
            System.out.println(d);
        }
    }
}
