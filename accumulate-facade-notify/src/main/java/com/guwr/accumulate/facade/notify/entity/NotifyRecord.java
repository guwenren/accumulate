package com.guwr.accumulate.facade.notify.entity;

import com.guwr.accumulate.common.entity.BaseEntity;
import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by gwr
 * Path com.guwr.accumulate.facade.notify.entity.NotifyRecord
 * Date 2016/8/21
 * Time 14:32
 * Description  通知表
 */
@Entity
@Table(name = "tbl_notify_record")
public class NotifyRecord extends BaseEntity {


    private static final long serialVersionUID = 316294188572441683L;

    @Column(name = "notify_times")
    private Integer notifyTimes = 0;//通知次数
    @Column(name = "limit_notify_times")
    private Integer limitNotifyTimes = 5;//限制通知次数
    private String url;//通知URL
    @Column(name = "merchant_no")
    private String merchantNo;//商户编号
    @Column(name = "merchant_order_no")
    private String merchantOrderNo;//商户订单号
    private Integer status = 101;//通知状态(100:成功:101:未成功,默认101)
    @Column(name = "notify_type")
    private Integer notifyType = 0;//通知类型 NotifyTypeEnum

    public Integer getNotifyTimes() {
        return notifyTimes;
    }

    public void setNotifyTimes(Integer notifyTimes) {
        this.notifyTimes = notifyTimes;
    }

    public Integer getLimitNotifyTimes() {
        return limitNotifyTimes;
    }

    public void setLimitNotifyTimes(Integer limitNotifyTimes) {
        this.limitNotifyTimes = limitNotifyTimes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(Integer notifyType) {
        this.notifyType = notifyType;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
