package com.guwr.accumulate.common.enums;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.common.enums.NotifyDestination
 * Date         2016/11/24
 * Time         14:24
 * Description   消息队列名称
 */
public enum NotifyDestination {

    MESSAGE_NOTIFY("消息通知"),
    USER_PRODUCT_LEVEL_MESSAGE("用户投资级别消息"),
    UPDATE_PROEARN_INTERESTRATE_MESSAGE("修改投资预期收益与利率");
    /**
     * 描述
     */
    private String desc;

    NotifyDestination(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
