package com.guwr.accumulate.test.concurrent.futuretask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.concurrent.futuretask.FutureTaskForMultiCompute
 * Date         2016/11/30
 * Time         19:10
 * Description  多任务计算
 */
public class FutureTaskForMultiCompute {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建任务集合
        List<FutureTask<Integer>> taskList = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 开始统计各计算线程计算结果
        Integer totalResult = 0;

        for (int i = 0; i < 100; i++) {
            FutureTask<Integer> task = new FutureTask<>(new ComputeTask(i, "" + i));
            executorService.submit(task);
            taskList.add(task);
        }
        for (FutureTask<Integer> futureTask : taskList) {
            totalResult = totalResult + futureTask.get();
            System.out.println("futureTask.get() = " + futureTask.get());
            System.out.println("totalResult = " + totalResult);
        }
    }
}
