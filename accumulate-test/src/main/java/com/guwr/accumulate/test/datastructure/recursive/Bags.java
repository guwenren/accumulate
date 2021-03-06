package com.guwr.accumulate.test.datastructure.recursive;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.datastructure.recursive.Bags
 * Date         2017/2/22
 * Time         14:18
 * Description
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 演示用递归来解决背包问题
 */
public class Bags {
    /**
     * 用递归来解决背包问题
     * @param as 物品的重量的数组
     * @param targetWeight 背包的目标重量
     * @param nowIndex 当前已取出的索引
     * @param listResult 记录结果
     */
    public void bag(int[] as,int targetWeight,int nowIndex,List<Integer> listResult){
        if(nowIndex == as.length){
            return;
        }

        if(as[nowIndex] > targetWeight){
            //跳过，判断下一个物品是否合适
            this.bag(as, targetWeight, ++nowIndex, listResult);
        }else if(as[nowIndex] == targetWeight){
            listResult.add(as[nowIndex]);
            System.out.println("one result===="+listResult);
            listResult.clear();
        }else{
            listResult.add(as[nowIndex]);
            this.bag(as, targetWeight-as[nowIndex], ++nowIndex, listResult);
        }
    }
    public static void main(String[] args) {
        Bags t = new Bags();

        int[] as = new int[]{11,8,7,5};

        for(int i=0;i<as.length;i++){
            t.bag(as, 16, i, new ArrayList<Integer>());
        }
    }
}

