package com.guwr.accumulate.test.datastructure.stack;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.stack.Reverse
 * Date         2017/2/16
 * Time         16:13
 * Description  利用栈反转字符串
 */
public class Reverse {
    private int[] datas;
    private int topIndex = -1;

    public static void main(String[] args) {
        Reverse t = new Reverse();
        String ret = t.doRev("this is 阿  test");
        System.out.println("ret===" + ret);
    }

    public String doRev(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = str.toCharArray();
        MyStack myStack = new MyStack(20);
        for (char aChar : chars) {
            myStack.push(aChar);
        }
        while (!myStack.isEmpty()) {
            stringBuilder.append((char) myStack.pop());
        }
        return stringBuilder.toString();
    }
}
