package com.guwr.accumulate.facade.user.enums;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.facade.user.enums.UserProductInvestLogInvestType
 * Date         2016/11/19
 * Time         18:53
 * Description
 */
public enum UserProductInvestLogInvestType {
    OUT("增加", 1),
    IN("减少", 2);

    private String desc;
    private int value;

    UserProductInvestLogInvestType(String desc, int value) {
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
