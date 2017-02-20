package com.guwr.accumulate.test.datastructure.queue;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.queue.PriorityQueue
 * Date         2017/2/17
 * Time         14:50
 * Description  优先队列
 */
public class PriorityQueue {
    private int[] queue;

    private int nItems;

    public PriorityQueue(int length) {
        queue = new int[length];
        nItems = 0;
    }

    public static void main(String[] args) {
        PriorityQueue t = new PriorityQueue(5);

        t.insert(5);
        t.insert(6);
        t.insert(3);
        t.insert(4);
        t.insert(1);

        t.printQueue();

        int ret = t.peekFront();
        System.out.println("now ret==" + ret);

        t.remove();
        int ret2 = t.remove();
        System.out.println("now ret2==" + ret2);

        t.printQueue();
    }

    private void insert(int data) {
        if (nItems == 0) {
            queue[nItems] = data;
            nItems++;
        } else {
            int i;
            for (i = nItems - 1; i >= 0; i--) {
                if (data < queue[i]) {
                    queue[i + 1] = queue[i];
                } else {
                    break;
                }
            }
            queue[i + 1] = data;
            nItems++;
        }
    }

    private int remove() {
        nItems--;
        int temp = queue[nItems];
        queue[nItems] = 0;
        return temp;
    }

    private int peekFront() {
        return queue[nItems - 1];
    }

    private boolean isEmpty() {
        return nItems == 0;
    }

    private boolean isFull() {
        return nItems == queue.length;
    }

    private void printQueue() {
        System.out.println("=======================>");
        for (int d : queue) {
            System.out.println(d);
        }
    }
}
