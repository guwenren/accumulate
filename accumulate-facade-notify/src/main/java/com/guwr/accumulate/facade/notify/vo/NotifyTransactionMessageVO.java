package com.guwr.accumulate.facade.notify.vo;

import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.common.web.vo.BaseVO;

import java.util.Date;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.facade.notify.vo.NotifyTransactionMessageVO
 * Date         2016/11/6
 * Time         16:53
 * Description
 */
public class NotifyTransactionMessageVO extends BaseVO {


    private static final long serialVersionUID = 2773955965211920688L;
    private Integer status;
    private String messageBody;
    private Integer areadlyDead;
    private String createTimeBefore; //

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Integer getAreadlyDead() {
        return areadlyDead;
    }

    public void setAreadlyDead(Integer areadlyDead) {
        this.areadlyDead = areadlyDead;
    }

    public String getCreateTimeBefore() {
        return createTimeBefore;
    }

    public void setCreateTimeBefore(String createTimeBefore) {
        this.createTimeBefore = createTimeBefore;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
