package com.guwr.accumulate.facade.wmps.enums;

import java.io.Serializable;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.facade.wmps.enums.ProductRecordStatus
 * Date         2016/11/17
 * Time         14:09
 * Description
 */
public enum ProductRecordStatus implements Serializable {
    // 产品购买状态
    WMPS_BUY_RECORD_STATUS_SUCCESS("成功", 1),
    WMPS_BUY_RECORD_STATUS_SUCCESS_PART("部分成功", 2),
    WMPS_BUY_RECORD_STATUS_FAIL("失败", 3),
    WMPS_BUY_RECORD_STATUS_EXIT("退出", 4),
    WMPS_BUY_RECORD_STATUS_ASSING("债权转让中", 5),
    WMPS_BUY_RECORD_STATUS_ASSI("债权转让成功", 6),
    WMPS_BUY_RECORD_STATUS_INTER("计息中", 7),
    WMPS_BUY_RECORD_STATUS_END("结束完成", 8),
    WMPS_BUY_RECORD_STATUS_EXITCHECK("退出审核中", 9);

    private String desc;
    private int value;

    ProductRecordStatus(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
