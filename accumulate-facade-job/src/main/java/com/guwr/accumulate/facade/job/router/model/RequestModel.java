package com.guwr.accumulate.facade.job.router.model;

import com.guwr.accumulate.common.util.CommonUtils;

import java.io.Serializable;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.job.router.model.RequestModel
 * Date 2016/9/27
 * Time 11:53
 */
public class RequestModel implements Serializable{

    private static final long serialVersionUID = 3691742714576262497L;
    private long timestamp;
    private String action;

    private String jobGroup;
    private String jobName;

    private String executorHandler;
    private String executorParams;

    private boolean glueSwitch;

    private String logAddress;
    private int logId;
    private long logDateTim;

    private String status;
    private String msg;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getExecutorHandler() {
        return executorHandler;
    }

    public void setExecutorHandler(String executorHandler) {
        this.executorHandler = executorHandler;
    }

    public String getExecutorParams() {
        return executorParams;
    }

    public void setExecutorParams(String executorParams) {
        this.executorParams = executorParams;
    }

    public boolean isGlueSwitch() {
        return glueSwitch;
    }

    public void setGlueSwitch(boolean glueSwitch) {
        this.glueSwitch = glueSwitch;
    }

    public String getLogAddress() {
        return logAddress;
    }

    public void setLogAddress(String logAddress) {
        this.logAddress = logAddress;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public long getLogDateTim() {
        return logDateTim;
    }

    public void setLogDateTim(long logDateTim) {
        this.logDateTim = logDateTim;
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
