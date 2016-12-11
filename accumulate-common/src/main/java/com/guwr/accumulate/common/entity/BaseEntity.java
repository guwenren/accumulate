package com.guwr.accumulate.common.entity;

import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.common.entity.BaseEntity
 * Date         2016/11/6
 * Time         10:07
 * Description
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {


    private static final long serialVersionUID = 8659798700451447745L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Integer version;    //版本
    @Column(name = "create_time")
    private Date createTime; //创建时间
    @Column(name = "update_time")
    private Date updateTime; //更新时间
    private String uuid;//uuid

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
