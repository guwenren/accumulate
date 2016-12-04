package com.guwr.accumulate.test.concurrent.futuretask;

import java.util.concurrent.Callable;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.test.concurrent.futuretask.ComputeTask
 * Date         2016/11/30
 * Time         17:28
 * Description
 */
public class ComputeTask implements Callable<Integer> {

    public Integer result;

    private String taskName;

    public ComputeTask(Integer result, String taskName) {
        this.result = result;
        this.taskName = taskName;
    }

    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 100; i++) {
            result = result + i;
        }

        Thread.sleep(5000);
        System.out.println("子线程计算任务: " + taskName + " 执行完成!");
        return result;
    }
}
