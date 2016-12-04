package com.guwr.accumulate.facade.wmps.enums;

import java.io.Serializable;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.facade.wmps.enums.ProductStatus
 * Date         2016/11/17
 * Time         14:09
 * Description
 */
public enum ProductStatus implements Serializable {
    PUBLISHING("尚未发布", 0),
    PUBLISHED("已发布，认购中 ", 1),
    AUDITING("认购完成，审核中", 2),
    INTERESTING("计息中", 3),
    FINSHED("到期完成", 4);

    private String desc;
    private int value;

    ProductStatus(String desc, int value) {
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
