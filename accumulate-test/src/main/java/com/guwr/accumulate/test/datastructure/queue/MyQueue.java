package com.guwr.accumulate.test.datastructure.queue;

import com.alibaba.fastjson.JSON;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.queue.MyQueue
 * Date         2017/2/17
 * Time         14:50
 * Description  循环队列
 */
public class MyQueue {
    private int[] queue;
    private int front;
    private int end;
    private int nItems;

    public MyQueue(int length) {
        queue = new int[length];
        front = 0;
        end = -1;
        nItems = 0;
    }

    public static void main(String[] args) {
        MyQueue t = new MyQueue(5);

        t.insert(1);
        t.insert(2);
        t.insert(3);
        t.insert(4);
        t.insert(5);

        t.printQueue();

        int ret = t.peekFront();
        System.out.println("now ret=="+ret);

        t.remove();
        int ret2 = t.remove();
        System.out.println("now ret2=="+ret2);

        t.printQueue();

        t.insert(6);
        t.insert(7);
        t.insert(8);
        t.printQueue();
    }

    public void insert(int data) {
        if (end == queue.length - 1) {
            end = -1;
        }
        end++;
        queue[end] = data;
        nItems++;
        if (nItems > queue.length) {
            nItems = queue.length;
        }
    }

    public int remove() {
        if (front == queue.length - 1) {
            front = 0;
        }
        int temp = queue[front];
        queue[front] = 0;
        front++;
        nItems--;
        return temp;
    }

    public int peekFront() {
        return queue[front];
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public boolean isFull() {
        return nItems == queue.length;
    }

    public void printQueue() {
        System.out.println("=======================>");
        for (int d : queue) {
            System.out.println(d);
        }

        System.out.println(JSON.toJSONString(this));
    }
}
