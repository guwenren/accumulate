package com.guwr.accumulate.test.datastructure.array.noorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.array.noorder.OperateNoIndexRepeatable
 * Date         2017/2/14
 * Time         17:18
 * Description  根据数据操作数组-有重复的
 */
public class OperateNoIndexRepeatable {
    private int[] datas;
    private int currentIndex = 0;

    public OperateNoIndexRepeatable(int length) {
        this.datas = new int[length];
    }

    public static void main(String[] args) {
        OperateNoIndexRepeatable t = new OperateNoIndexRepeatable(10);

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

    private int insert(int data) {
        datas[currentIndex] = data;
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

    private void remove(int data) {
        int index = getIndex(0, data);
        while (index >= 0) {
            // 从需要删除的索引开始，将后面的元素一个个向前移一位
            for (int i = index; i < currentIndex; i++) {
                datas[i] = datas[i + 1];
//                break;
            }
            currentIndex--;
            index = getIndex(index, data);
        }
    }

    private List<Integer> searchOne(int data) {
        List<Integer> integerList = new ArrayList<>();
        int index = getIndex(0, data);
        while (index >= 0) {
            integerList.add(datas[index]);
            index = getIndex(index + 1, data);
        }
        return integerList;
    }

    private void printDatas() {
        System.out.println("======================================>");
        for (int d : datas) {
            System.out.println(d);
        }
    }
}
