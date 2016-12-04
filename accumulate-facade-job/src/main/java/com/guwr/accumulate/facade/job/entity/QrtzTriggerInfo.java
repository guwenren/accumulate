package com.guwr.accumulate.facade.job.entity;

import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.job.entity.QrtzTriggerInfo
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_job_qrtz_trigger_info")
public class QrtzTriggerInfo implements Serializable {


    private static final long serialVersionUID = -1309600883062868382L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // job info
    @Column(name = "job_group")
    private String jobGroup;
    @Column(name = "job_name")
    private String jobName;
    @Column(name = "job_cron")
    private String jobCron;
    @Column(name = "job_desc")
    private String jobDesc;

    @Column(name = "add_time")
    private Date addTime;
    @Column(name = "update_time")
    private Date updateTime;

    private String author;
    @Column(name = "alarm_email")
    private String alarmEmail;

    @Column(name = "executor_address")
    private String executorAddress;    // 执行器地址，有多个则逗号分隔
    @Column(name = "executor_handler")
    private String executorHandler;    // 执行器Handler
    @Column(name = "executor_param")
    private String executorParam;    // 执行器，任务参数

    @Column(name = "glue_switch")
    private Integer glueSwitch;    // 执行器地址，有多个则逗号分隔
    @Column(name = "glue_source")
    private String glueSource;    // 执行器Handler
    @Column(name = "glue_remark")
    private String glueRemark;    // 执行器，任务参数
    @Column(name = "child_jobkey")
    private String childJobkey;    // 执行器，任务参数

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

    public String getJobCron() {
        return jobCron;
    }

    public void setJobCron(String jobCron) {
        this.jobCron = jobCron;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlarmEmail() {
        return alarmEmail;
    }

    public void setAlarmEmail(String alarmEmail) {
        this.alarmEmail = alarmEmail;
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

    public Integer getGlueSwitch() {
        return glueSwitch;
    }

    public void setGlueSwitch(Integer glueSwitch) {
        this.glueSwitch = glueSwitch;
    }

    public String getGlueSource() {
        return glueSource;
    }

    public void setGlueSource(String glueSource) {
        this.glueSource = glueSource;
    }

    public String getGlueRemark() {
        return glueRemark;
    }

    public void setGlueRemark(String glueRemark) {
        this.glueRemark = glueRemark;
    }

    public String getChildJobkey() {
        return childJobkey;
    }

    public void setChildJobkey(String childJobkey) {
        this.childJobkey = childJobkey;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
