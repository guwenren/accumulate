package com.guwr.accumulate.facade.notify.enums;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.facade.notify.enums.NotifyMessageStatus
 * Date         2016/11/23
 * Time         19:58
 * Description
 */
public enum NotifyMessageStatus {
    WAITING_CONFIRM("待确认", 0),
    ALREADY_CONFIRM("已确认", 1);
    private String desc;
    private int value;

    NotifyMessageStatus(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
