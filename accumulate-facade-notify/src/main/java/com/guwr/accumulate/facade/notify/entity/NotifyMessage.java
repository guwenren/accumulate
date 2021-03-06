package com.guwr.accumulate.facade.notify.entity;

import com.guwr.accumulate.common.entity.BaseEntity;
import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.notify.entity.NotifyMessage
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_notify__message")
public class NotifyMessage extends BaseEntity {

    private static final long serialVersionUID = 7206021153093420017L;

    @Column(name = "message_body")
    private String messageBody; //消息内容
    @Column(name = "consumer_queue")
    private String consumerQueue;//消费队列
    @Column(name = "message_send_times")
    private Integer messageSendTimes; //消息重发次数
    @Column(name = "areadly_dead")
    private Integer areadlyDead; //是否死亡
    private Integer status;//状态
    private String remark; //备注

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getConsumerQueue() {
        return consumerQueue;
    }

    public void setConsumerQueue(String consumerQueue) {
        this.consumerQueue = consumerQueue;
    }

    public Integer getMessageSendTimes() {
        return messageSendTimes;
    }

    public void setMessageSendTimes(Integer messageSendTimes) {
        this.messageSendTimes = messageSendTimes;
    }

    public Integer getAreadlyDead() {
        return areadlyDead;
    }

    public void setAreadlyDead(Integer areadlyDead) {
        this.areadlyDead = areadlyDead;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
