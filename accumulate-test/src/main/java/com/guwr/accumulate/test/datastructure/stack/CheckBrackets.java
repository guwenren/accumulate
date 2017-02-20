package com.guwr.accumulate.test.datastructure.stack;

import java.util.Objects;
import java.util.Queue;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.stack.Reverse
 * Date         2017/2/16
 * Time         16:13
 * Description  利用栈检查字符串括号是否匹配
 */
public class CheckBrackets {

    public static void main(String[] args) {
        CheckBrackets t = new CheckBrackets();
        t.check("(3+2)/5-[(7+8)*4-5]");
    }

    private void check(String s) {
        char[] chars = s.toCharArray();
        MyStack myStack = new MyStack(50);
        for (char aChar : chars) {
            if (Objects.equals('{', aChar) || Objects.equals('[', aChar) || Objects.equals('(', aChar)) {
                myStack.push(aChar);
            } else if (Objects.equals('}', aChar) || Objects.equals(']', aChar) || Objects.equals(')', aChar)) {
                char msc = (char) myStack.pop();
                if((msc=='{' && aChar!='}') || 	(msc=='[' && aChar!=']') || (msc=='(' && aChar!=')')){
                    System.out.println("sorry，匹配不成功，出错的位置=="+(aChar+1)+" 个字符处");
                }else {
                    System.out.println("匹配成功");
                }
            }
        }
    }
}
