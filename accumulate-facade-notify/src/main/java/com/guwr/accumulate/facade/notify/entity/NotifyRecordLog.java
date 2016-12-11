package com.guwr.accumulate.facade.notify.entity;

import com.guwr.accumulate.common.entity.BaseEntity;
import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.facade.notify.entity.NotifyRecordLog
 * Date         2016/11/6
 * Time         11:44
 * Description  通知日志表
 */
@Entity
@Table(name = "tbl_notify_record_log")
public class NotifyRecordLog extends BaseEntity {


    private static final long serialVersionUID = 7082630535924694869L;

    @Column(name = "notify_id")
    private Integer notifyId;//通知记录ID
    private String request;//请求信息
    private String response;//返回信息
    @Column(name = "merchant_no")
    private String merchantNo;//商户编号
    @Column(name = "merchant_order_no")
    private String merchantOrderNo;//商户订单号
    @Column(name = "http_status")
    private Integer httpStatus;//HTTP状态


    public Integer getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Integer notifyId) {
        this.notifyId = notifyId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
