package com.guwr.accumulate.service.wmps.task;

import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.facade.wmps.entity.ProductRecordExtend;
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

    private Integer mod; //mod数字
    private Integer number;//线程编号
    private IProductRecordService productRecordService;
    private Integer interestDate;//计息时间

    public InterestTask(Integer mod, Integer number, IProductRecordService productRecordService, Integer interestDate) {
        this.mod = mod;
        this.number = number;
        this.productRecordService = productRecordService;
        this.interestDate = interestDate;
    }

    @Override
    public Integer call() throws Exception {
        List<ProductRecordExtend> productRecordExtendListByMOD = productRecordService.findProductRecordExtendListByMOD(mod, number, interestDate);
        System.out.println("number = " + number + ",productRecordExtendListByMOD = " + JSON.toJSONString(productRecordExtendListByMOD));
        return null;
    }

}
