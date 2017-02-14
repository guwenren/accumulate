package com.guwr.accumulate.test.datastructure.array.noorder;

import java.util.Objects;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.array.noorder.OperateNoIndex
 * Date         2017/2/14
 * Time         16:43
 * Description  根据数据操作数组
 */
public class OperateNoIndex {
    private int[] datas;
    private int currentIndex = 0;

    public OperateNoIndex(int length) {
        this.datas = new int[length];
    }

    public static void main(String[] args) {
        OperateNoIndex t = new OperateNoIndex(10);

        t.insert(3);
        t.insert(6);
        t.insert(1);
        t.insert(2);
        t.printDatas();
        t.remove(1);
        t.printDatas();

        int ret = t.searchOne(2);
        System.out.println("ret==" + ret);
    }

    private int insert(int data) {
        datas[currentIndex] = data;
        currentIndex++;
        return currentIndex - 1;
    }

    private int getIndex(int data) {
        int index = -1;
        for (int i = 0; i < currentIndex; i++) {
            if (Objects.equals(data, datas[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void remove(int data) {
        int index = getIndex(data);
        // 从需要删除的索引开始，将后面的元素一个个向前移一位
        for (int i = index; i < currentIndex; i++) {
            datas[i] = datas[i + 1];
        }
        currentIndex--;
    }

    public int searchOne(int data) {
        int index = getIndex(data);
        if (index >= 0) {
            return datas[index];
        }
        return 0;
    }

    public void printDatas() {
        System.out.println("======================================>");
        for (int d : datas) {
            System.out.println(d);
        }
    }
}
