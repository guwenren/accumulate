package com.guwr.accumulate.facade.job.router.model;

import com.guwr.accumulate.common.util.CommonUtils;

import java.io.Serializable;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.job.router.model.ResponseModel
 * Date 2016/9/27
 * Time 11:53
 */
public class ResponseModel implements Serializable {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";

    private String status;
    private String msg;

    public ResponseModel() {
    }

    public ResponseModel(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
