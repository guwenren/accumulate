package com.guwr.accumulate.common.util;

import java.io.Serializable;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.common.util.ResultData
 * Date 2016/9/3
 * Time 15:38
 */
public class ResultData implements Serializable {

    private static final long serialVersionUID = 6276349388857903930L;
    public static final ResultData SUCCESS = new ResultData();
    private int code = 0;
    private String msg;
    private Object content;

    public ResultData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResultData(Object content) {
        this.content = content;
    }
    public ResultData() {
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getContent() {
        return content;
    }
    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
