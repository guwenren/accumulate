package com.guwr.accumulate.test.datastructure.stack;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.stack.MyStack
 * Date         2017/2/16
 * Time         16:13
 * Description  简单栈操作
 */
public class MyStack {
    private int[] datas;
    private int topIndex = -1;

    public MyStack(int length) {
        datas = new int[length];
    }

    public static void main(String[] args) {
        MyStack t = new MyStack(10);

        t.push(3);
        t.push(5);
        t.push(9);
        t.push(2);

        t.printStack();

        int ret1 = t.peek();
        System.out.println("ret1==" + ret1);

        t.pop();

        t.printStack();
        int ret2 = t.pop();
        System.out.println("ret2==" + ret2);

        t.printStack();
    }

    public void push(int data) {
        topIndex++;
        datas[topIndex] = data;
    }

    public int pop() {
        int ret = datas[topIndex];
        topIndex--;
        return ret;
    }

    public int peek() {
        return datas[topIndex];
    }

    public boolean isEmpty() {
        return topIndex == -1;
    }

    public boolean isFull() {
        return datas.length == (topIndex + 1);
    }

    public void printStack() {
        System.out.println("====================>");
        for (int i = 0; i <= topIndex; i++) {
            System.out.println(datas[i]);
        }
    }
}
