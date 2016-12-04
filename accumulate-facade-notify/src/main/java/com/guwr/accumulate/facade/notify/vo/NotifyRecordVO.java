package com.guwr.accumulate.facade.notify.vo;

import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.common.web.vo.BaseVO;

import java.util.List;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.facade.notify.vo.NotifyRecordVO
 * Date         2016/11/6
 * Time         16:53
 * Description
 */
public class NotifyRecordVO extends BaseVO {
    private List<Integer> status;
    private List<Integer> notifyTimes;

    public List<Integer> getStatus() {
        return status;
    }

    public void setStatus(List<Integer> status) {
        this.status = status;
    }

    public List<Integer> getNotifyTimes() {
        return notifyTimes;
    }

    public void setNotifyTimes(List<Integer> notifyTimes) {
        this.notifyTimes = notifyTimes;
    }
    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
