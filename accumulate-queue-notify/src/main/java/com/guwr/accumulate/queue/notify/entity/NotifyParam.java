package com.guwr.accumulate.queue.notify.entity;

import com.guwr.accumulate.common.util.CommonUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.queue.notify.entity.NotifyParam
 * Date         2016/11/6
 * Time         15:01
 * Description
 */
public class NotifyParam implements Serializable {
    private Map<Integer, Integer> notifyParams;// 通知时间次数map
    private String successValue;// 通知后用于判断是否成功的返回值。由HttpResponse获取

    public Map<Integer, Integer> getNotifyParams() {
        return notifyParams;
    }

    public void setNotifyParams(Map<Integer, Integer> notifyParams) {
        this.notifyParams = notifyParams;
    }

    public String getSuccessValue() {
        return successValue;
    }

    public void setSuccessValue(String successValue) {
        this.successValue = successValue;
    }

    public Integer getMaxNotifyTime() {
        return notifyParams == null ? 0 : notifyParams.size();
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
