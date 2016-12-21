package com.guwr.accumulate.service.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.el.borrow.soa.domain.borrowauth.vo.BorrowAuthVo;
import com.el.borrow.soa.domain.borrowsignature.model.BorrowSignature;
import com.el.borrow.soa.domain.borrowsignature.vo.BorrowSignatureVo;
import com.el.borrow.soa.service.logic.borrowauth.ILogicBorrowAuthService;
import com.el.borrow.soa.service.logic.borrowsignature.ILogicBorrowSignatureService;
import com.el.common.page.PageList;
import com.el.common.result.PublicResult;
import com.eloancn.app.IBorrowAppAPI;
import com.eloancn.entity.repay.RepayShowVO;
import com.eloancn.fornew.result.Result;
import com.eloancn.overdueAccrue.OverdueAccrueAPI;
import com.eloancn.repayment.OverdueRepaymentAPI;
import com.eloancn.repayment.PayBackBailAPI;
import com.eloancn.repayment.RepaymentAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DubboProvider {
    private static Logger LOGGER = LoggerFactory.getLogger(DubboProvider.class);

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
            System.out.println("accumulate-service-test");
            System.out.println("context = " + context);
//            getBorrowSignature(context);
            getRepayShow(context);
//            autoRepayOverdue(context);
//            gotOrAccruedByTid(context);
//            countAccruedByUid(context);
//            borrowAPP(context);
//            countPayBackBail(context);
            context.start();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("== DubboProvider context start error:", e);
        }

//        synchronized (DubboProvider.class) {
//            while (true) {
//                try {
//                    DubboProvider.class.wait();
//                } catch (InterruptedException e) {
//                    LOGGER.error("== synchronized error:", e);
//                }
//            }
//        }
    }

    /**
     * 正在还款的借款
     */
    private static void getBorrowSignature(ClassPathXmlApplicationContext context) {
        System.out.println("context = [" + context + "]");
        System.out.println("DubboProvider.getBorrowSignature");
        BorrowSignatureVo info = new BorrowSignatureVo();
        info.setTid(1045132);
        info.setUtype(2);
        ILogicBorrowSignatureService api = context.getBean("logicBorrowSignatureService", ILogicBorrowSignatureService.class);
        PublicResult<PageList<BorrowSignatureVo>> result = api.newgetBorrowSignature(info);
//        PublicResult<PageList<BorrowSignature>> result = (PublicResult<PageList<BorrowSignature>>) pageListPublicResult;
        System.out.println("result = " + JSON.toJSONString(result));
    }

    /**
     * 正在还款的借款
     */
    private static void countPayBackBail(ClassPathXmlApplicationContext context) {
        System.out.println("context = [" + context + "]");
        System.out.println("DubboProvider.getRepayShow");
        PayBackBailAPI api = context.getBean("payBackBailAPI", PayBackBailAPI.class);

        HashMap<String, Object> map = new HashMap<>();
        map.put("tenderId", 1028468);
        Result result = api.countPayBackBail(map);
        System.out.println("result = " + JSON.toJSONString(result));
    }

    /**
     * 正在还款的借款
     */
    private static void getRepayShow(ClassPathXmlApplicationContext context) {
        System.out.println("context = [" + context + "]");
        System.out.println("DubboProvider.getRepayShow");
        RepaymentAPI api = context.getBean("repaymentAPI", RepaymentAPI.class);

        com.eloancn.entity.Result<List<RepayShowVO>> result = api.getRepayShowByUid(1044098);
        System.out.println("result = " + JSON.toJSONString(result));

        Map<String,Object> mapParam = new HashMap<>();
        mapParam.put("status",7);
        mapParam.put("uid",202);
        com.eloancn.entity.Result<List<RepayShowVO>> result1 = api.getRepayShowByUid(mapParam);
        System.out.println("result1 = " + JSON.toJSONString(result1));
    }

    /**
     * 充值自动还款
     */
    private static void autoRepayOverdue(ClassPathXmlApplicationContext context) {
        System.out.println("context = [" + context + "]");
        System.out.println("DubboProvider.autoRepayOverdue");
        OverdueRepaymentAPI api = context.getBean("overdueRepaymentAPI", OverdueRepaymentAPI.class);
        com.eloancn.entity.Result<Object> objectResult = api.autoRepayOverdue(709595);
        System.out.println("result = " + JSON.toJSONString(objectResult));
    }


    /**
     * 逾期还款初始化
     */
    private static void gotOrAccruedByTid(ClassPathXmlApplicationContext context) {
        System.out.println("DubboProvider.gotOrAccruedByTid");
        OverdueAccrueAPI api = context.getBean("overdueAccrueAPI", OverdueAccrueAPI.class);
        Result result = api.gotOrAccruedByTid(1041043);
        System.out.println("result = " + JSON.toJSONString(result));
    }


    /**
     * 逾期还款初始化
     */
    private static void countAccruedByUid(ClassPathXmlApplicationContext context) {
        System.out.println("DubboProvider.countAccruedByUid");
        OverdueAccrueAPI api = context.getBean("overdueAccrueAPI", OverdueAccrueAPI.class);
        Result result = api.countAccruedByUid(710321);
        System.out.println("result = " + JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
    }

    private static void borrowAPP(ClassPathXmlApplicationContext context) {
        IBorrowAppAPI borrowAppAPI = context.getBean("borrowAppAPI", IBorrowAppAPI.class);
        System.out.println("borrowAppAPI = " + borrowAppAPI);

        Result result = borrowAppAPI.borroweTenderInfo(67, null);

        System.out.println("result = " + JSON.toJSONString(result));
        Integer uid = 707020;
        Integer tid = 1026390;
        Integer publisheddate = 1461901569;
        result = borrowAppAPI.borrowerInfo(tid, uid, publisheddate, null);
        System.out.println("result = " + JSON.toJSONString(result));
    }

    private static void borrow(ClassPathXmlApplicationContext context) {
        System.out.println("111");
        ILogicBorrowAuthService api = (ILogicBorrowAuthService) context.getBean("logicBorrowAuthService");
        ILogicBorrowSignatureService api1 = (ILogicBorrowSignatureService) context.getBean("logicBorrowSignatureService");
        System.out.println("222");
        System.out.println("api = " + api);
        BorrowAuthVo borrowAuthVo = new BorrowAuthVo();
        borrowAuthVo.setTid(1035180);

        PublicResult<ArrayList<BorrowAuthVo>> borrowAuthList = api.getBorrowAuthList(borrowAuthVo);
        System.out.println("JSON.toJSONString(borrowAuthList) = " + JSON.toJSONString(borrowAuthList));
        BorrowSignatureVo info = new BorrowSignatureVo();
        info.setUtype(2);
        info.setTid(1035180);
        PublicResult<PageList<BorrowSignature>> borrowSignature = api1.getBorrowSignature(info);
        System.out.println("JSON.toJSONString(borrowSignature) = " + JSON.toJSONString(borrowSignature));
    }
}