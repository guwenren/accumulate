package com.guwr.accumulate.service.wmps.task;

import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.service.wmps.core.service.IProductRecordService;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.service.wmps.task.InterestTask
 * Date         2016/12/14
 * Time         16:43
 * Description  发息Task
 */
public class InterestTask implements Callable<Integer> {

    private Integer number;//线程编号
    private IProductRecordService productRecordService;
    private Integer interestDate;//计息时间

    public InterestTask(Integer number, IProductRecordService productRecordService, Integer interestDate) {
        this.number = number;
        this.productRecordService = productRecordService;
        this.interestDate = interestDate;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("InterestTask.call.number = " + number);
        List<Integer> uids = productRecordService.findListByMOD(number,interestDate);
        System.out.println("JSON.toJSONString(uids) = " + JSON.toJSONString(uids));
        return null;
    }
}
