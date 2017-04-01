package com.guwr.accumulate.queue.notify.core;

import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;
import com.guwr.accumulate.common.exception.BaseException;
import com.guwr.accumulate.facade.notify.entity.NotifyRecord;
import com.guwr.accumulate.facade.notify.enums.NotifyRecordEnum;
import com.guwr.accumulate.queue.notify.entity.NotifyParam;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.queue.notify.core.NotifyTask
 * Date         2016/11/6
 * Time         15:09
 * Description
 */
public class NotifyTask implements Runnable, Delayed, Serializable {

    private static final long serialVersionUID = 5783772923749570643L;

    private static Logger logger = LoggerFactory.getLogger(NotifyTask.class);
    //执行时间
    private long executeTime;
    //执行间隔时间以及默认成功返回值
    private NotifyParam notifyParam;
    private NotifyRecord notifyRecord;
    private NotifyQueue notifyQueue;

    @Autowired
    private NotifyPersist notifyPersist;

    public NotifyTask() {

    }

    public NotifyTask(NotifyRecord notifyRecord, NotifyQueue notifyQueue, NotifyParam notifyParam) {
        super();
        this.notifyRecord = notifyRecord;
        this.notifyQueue = notifyQueue;
        this.notifyParam = notifyParam;
        this.executeTime = getExecuteTime(notifyRecord);
    }

    private long getExecuteTime(NotifyRecord notifyRecord) {
        //上次最后执行时间
        long lastTime = notifyRecord.getUpdateTime().getTime();
        //根据当前执行次数算出下次执行时间
        Integer nextNotifyTime = notifyParam.getNotifyParams().get(notifyRecord.getNotifyTimes());
        return (nextNotifyTime == null ? 0 : nextNotifyTime * 1000) + lastTime;
    }

    @Override
    public void run() {
        logger.info("NotifyTask.run");
        Integer id = notifyRecord.getId();
        try {
            String url = notifyRecord.getUrl();
            // 得到当前通知对象的通知次数
            Integer notifyTimes = notifyRecord.getNotifyTimes();
            logger.info("Notify Url " + url);
            HttpResponse response = HttpRequest.post(url).send();
            notifyRecord.setNotifyTimes(notifyTimes + 1);
            String successValue = notifyParam.getSuccessValue();
            String responseMsg = "";
            Integer responseStatus = response.statusCode();
            // 得到返回状态，如果是200，也就是通知成功
            List<Integer> status = Arrays.asList(200, 201, 202, 203, 204, 205, 206);
            if (status.contains(responseStatus)) {
                responseMsg = response.body();
                responseMsg = responseMsg.length() >= 600 ? responseMsg.substring(0, 600) : responseMsg;
                logger.info("订单号： " + notifyRecord.getMerchantOrderNo() + " HTTP_STATUS：" + responseStatus + "请求返回信息：" + responseMsg);
                // 通知成功
                if (responseMsg.trim().equals(successValue)) {
                    notifyPersist.updateNotifyRord(id, notifyTimes, NotifyRecordEnum.NotifyRecordEnumStatus.SUCCESS.getValue());
                } else {
                    notifyQueue.addElementToList(notifyRecord);
                    notifyPersist.updateNotifyRord(id, notifyTimes, NotifyRecordEnum.NotifyRecordEnumStatus.HTTP_REQUEST_SUCCESS.getValue());
                }
                logger.info("Update NotifyRecord:" + JSON.toJSONString(notifyRecord));
            } else {
                notifyQueue.addElementToList(notifyRecord);
                // 再次放到通知列表中，由添加程序判断是否已经通知完毕或者通知失败
                notifyPersist.updateNotifyRord(id, notifyTimes, NotifyRecordEnum.NotifyRecordEnumStatus.HTTP_REQUEST_FALIED.getValue());
            }

            // 写通知日志表
            notifyPersist.saveNotifyRecordLogs(id, notifyRecord.getMerchantNo(), notifyRecord.getMerchantOrderNo(),
                    url, responseMsg, responseStatus);
            logger.info("Insert NotifyRecordLog, merchantNo:" + notifyRecord.getMerchantNo() + ",merchantOrderNo:"
                    + notifyRecord.getMerchantOrderNo());

        } catch (RpcException e) {
            logger.error("NotifyTask", e);
            notifyQueue.addElementToList(notifyRecord);
        } catch (BaseException e) {
            logger.error("NotifyTask", e);
        } catch (Exception e) {
            logger.error("NotifyTask", e);
            notifyQueue.addElementToList(notifyRecord);
            notifyPersist.updateNotifyRord(notifyRecord.getId(), notifyRecord.getNotifyTimes(), NotifyRecordEnum.NotifyRecordEnumStatus.HTTP_REQUEST_FALIED.getValue());
            notifyPersist.saveNotifyRecordLogs(notifyRecord.getId(), notifyRecord.getMerchantNo(), notifyRecord.getMerchantOrderNo(), notifyRecord.getUrl(), "", 0);
        }
    }

    @Override
    public long getDelay(TimeUnit unit) {
        logger.info("NotifyTask.getDelay");
        return unit.convert(executeTime - System.currentTimeMillis(), TimeUnit.SECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        logger.info("NotifyTask.compareTo");
        NotifyTask task = (NotifyTask) o;
        return executeTime > task.executeTime ? 1 : (executeTime < task.executeTime ? -1 : 0);
    }

    public static void main(String[] args) {

        test2();
    }

    public static void test1() {
        long timeMillis = System.currentTimeMillis() + (60 * 1000);
        TimeUnit unit = TimeUnit.SECONDS;
        long convert = unit.convert(timeMillis - System.currentTimeMillis(), TimeUnit.SECONDS);
        System.out.println("convert = " + convert);
    }

    public static void test2() {
        String destination = "https://wap.eloancn.com/mobile/login.action";
        HttpRequest request = HttpRequest.post(destination);
        HttpResponse response = request.send();
        response.charset("utf-8");
        String body = response.body();
        System.out.println("body = " + body);
        System.out.println("JSON.toJSONString(response) = " + JSON.toJSONString(response));
        String queryString = request.bodyText();
        System.out.println("queryString = " + queryString);
        System.out.println(request.hostUrl());
    }
}
