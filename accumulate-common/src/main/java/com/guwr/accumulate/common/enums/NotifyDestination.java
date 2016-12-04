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
    /**
     * 消息通知
     */
    MESSAGE_NOTIFY("消息通知");
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
