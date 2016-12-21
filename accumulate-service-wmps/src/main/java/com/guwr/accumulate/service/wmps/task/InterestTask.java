package com.guwr.accumulate.service.wmps.task;

import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.common.util.StringUtils;
import com.guwr.accumulate.facade.user.entity.UserProductDayInter;
import com.guwr.accumulate.facade.user.entity.UserProductEarnings;
import com.guwr.accumulate.facade.user.entity.UserProductFundsInfo;
import com.guwr.accumulate.facade.user.facade.IUserProductDayInterFacade;
import com.guwr.accumulate.facade.user.facade.IUserProductEarningsFacade;
import com.guwr.accumulate.facade.user.facade.IUserProductFundsInfoFacade;
import com.guwr.accumulate.facade.wmps.entity.ProductRecordExtend;
import com.guwr.accumulate.service.wmps.core.service.IProductRecordService;

import java.math.BigDecimal;
import java.util.*;
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
    private IUserProductDayInterFacade userProductDayInterFacade;
    private IUserProductFundsInfoFacade userProductFundsInfoFacade;
    private IUserProductEarningsFacade userProductEarningsFacade;
    private Integer interestDate;//计息时间

    public InterestTask(Integer mod, Integer number, IProductRecordService productRecordService, Integer interestDate) {
        this.mod = mod;
        this.number = number;
        this.productRecordService = productRecordService;
        this.interestDate = interestDate;
    }

    public void setUserProductDayInterFacade(IUserProductDayInterFacade userProductDayInterFacade) {
        this.userProductDayInterFacade = userProductDayInterFacade;
    }

    public void setUserProductFundsInfoFacade(IUserProductFundsInfoFacade userProductFundsInfoFacade) {
        this.userProductFundsInfoFacade = userProductFundsInfoFacade;
    }

    public void setUserProductEarningsFacade(IUserProductEarningsFacade userProductEarningsFacade) {
        this.userProductEarningsFacade = userProductEarningsFacade;
    }

    @Override
    public Integer call() throws Exception {
        String uuid = StringUtils.getUUID();
        Date date = new Date();
        List<ProductRecordExtend> productRecordExtends = productRecordService.findProductRecordExtendListByMOD(mod, number, interestDate);
        System.out.println("number = " + number + ",productRecordExtends = " + JSON.toJSONString(productRecordExtends));
        // 每日用户总利息
        Map<Integer, BigDecimal> mapInter = new HashMap<>();
        List<UserProductDayInter> userProductDayInters = new ArrayList<>();
        List<UserProductFundsInfo> userProductFundsInfos = new ArrayList<>();

        for (ProductRecordExtend productRecordExtend : productRecordExtends) {
            Integer uid = productRecordExtend.getUid();
            Integer id = productRecordExtend.getId();
            Integer pid = productRecordExtend.getPid();
            BigDecimal interestrate = productRecordExtend.getInterestrate();//投资时vip利率
            BigDecimal inter = productRecordExtend.getInter();//每天利息
            BigDecimal mInter = mapInter.get(uid);
            if (mInter == null) {
                uuid = StringUtils.getUUID();
                UserProductDayInter userProductDayInter = new UserProductDayInter();
                userProductDayInter.setUid(uid);
                userProductDayInter.setStatus(1);
                userProductDayInter.setUuid(uuid);
                userProductDayInter.setCreateTime(date);
                userProductDayInter.setUpdateTime(date);
                userProductDayInters.add(userProductDayInter);
                mapInter.put(uid, inter);
            } else {
                mapInter.put(uid, inter.add(mInter));
            }
            UserProductFundsInfo userProductFundsInfo = new UserProductFundsInfo();
            userProductFundsInfo.setUid(uid);
            userProductFundsInfo.setPid(pid);
            userProductFundsInfo.setBid(id);
            userProductFundsInfo.setUuid(uuid);
            userProductFundsInfo.setInter(inter);
            userProductFundsInfo.setCreateTime(date);
            userProductFundsInfo.setUpdateTime(date);
            userProductFundsInfo.setInterestrate(interestrate);//vip利率
            userProductFundsInfos.add(userProductFundsInfo);
            //修改用户预期收益
            UserProductEarnings userProductEarnings = userProductEarningsFacade.findOneByUidPidInterestrate(uid, pid, interestrate);
            if (userProductEarnings != null) {
                BigDecimal realearn = userProductEarnings.getRealearn();
                BigDecimal sumRealearn = realearn.add(inter);
                userProductEarnings.setRealearn(sumRealearn);
                userProductEarningsFacade.update(userProductEarnings);
            }
        }
        setSumuInters(userProductDayInters, mapInter);//根据UID汇总每日计息
        userProductDayInterFacade.saveUserProductDayInters(userProductDayInters);
        userProductFundsInfoFacade.saveUserProductFundsInfos(userProductFundsInfos);
        return BigDecimal.ONE.intValue();
    }

    /**
     * 设置每日付息总金额
     */
    private void setSumuInters(List<UserProductDayInter> userProductDayInters, Map<Integer, BigDecimal> mapInter) {
        for (UserProductDayInter userProductDayInter : userProductDayInters) {
            Integer uid = userProductDayInter.getUid();
            userProductDayInter.setInter(mapInter.get(uid));
        }
    }
}
