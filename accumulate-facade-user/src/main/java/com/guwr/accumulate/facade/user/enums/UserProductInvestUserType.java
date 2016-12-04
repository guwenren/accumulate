package com.guwr.accumulate.facade.user.enums;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.facade.user.enums.UserProductInvestUserType
 * Date         2016/11/19
 * Time         18:53
 * Description
 */
public enum UserProductInvestUserType {
    USERTYPE_NORMAL("普通用户", 0),
    USERTYPE_NO_VIP("不享受VIP用户，认购中 ", 1),
    USERTYPE_FIXED_VIP("固定VIP用户", 2);

    private String desc;
    private int value;

    UserProductInvestUserType(String desc, int value) {
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
