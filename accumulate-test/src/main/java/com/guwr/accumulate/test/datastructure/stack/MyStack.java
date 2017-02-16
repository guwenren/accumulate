package com.guwr.accumulate.test.datastructure.stack;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.stack.MyStack
 * Date         2017/2/16
 * Time         16:13
 * Description
 */
public class MyStack {
    private int[] datas;
    private int topIndex = -1;

    public MyStack(int length) {
        datas = new int[length];
    }

    private void push(int data) {
        topIndex++;
        datas[topIndex] = data;
    }
}
