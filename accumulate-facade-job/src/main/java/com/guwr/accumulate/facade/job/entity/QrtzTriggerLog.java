package com.guwr.accumulate.facade.job.entity;

import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.job.entity.QrtzTriggerLog
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_job_qrtz_trigger_log")
public class QrtzTriggerLog implements Serializable {


    private static final long serialVersionUID = -1309600883062868382L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "job_group")
    private String jobGroup;
    @Column(name = "job_name")
    private String jobName;
    @Column(name = "executor_address")
    private String executorAddress;
    @Column(name = "executor_handler")
    private String executorHandler;
    @Column(name = "executor_param")
    private String executorParam;
    @Column(name = "trigger_time")
    private Date triggerTime;
    @Column(name = "trigger_status")
    private String triggerStatus;
    @Column(name = "trigger_msg")
    private String triggerMsg;
    @Column(name = "handle_time")
    private Date handleTime;
    @Column(name = "handle_status")
    private String handleStatus;
    @Column(name = "handle_msg")
    private String handleMsg;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getExecutorAddress() {
        return executorAddress;
    }

    public void setExecutorAddress(String executorAddress) {
        this.executorAddress = executorAddress;
    }

    public String getExecutorHandler() {
        return executorHandler;
    }

    public void setExecutorHandler(String executorHandler) {
        this.executorHandler = executorHandler;
    }

    public String getExecutorParam() {
        return executorParam;
    }

    public void setExecutorParam(String executorParam) {
        this.executorParam = executorParam;
    }

    public Date getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getTriggerStatus() {
        return triggerStatus;
    }

    public void setTriggerStatus(String triggerStatus) {
        this.triggerStatus = triggerStatus;
    }

    public String getTriggerMsg() {
        return triggerMsg;
    }

    public void setTriggerMsg(String triggerMsg) {
        this.triggerMsg = triggerMsg;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }

    public String getHandleMsg() {
        return handleMsg;
    }

    public void setHandleMsg(String handleMsg) {
        this.handleMsg = handleMsg;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
