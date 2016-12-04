package com.guwr.accumulate.facade.user.entity;

import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gwr
 * Description  用户投资收益表
 * Path com.guwr.accumulate.facade.wmps.entity.UserProductEarnings
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_user_wmps_earnings")
public class UserProductEarnings implements Serializable {


    private static final long serialVersionUID = 7057799382653381564L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Integer version;    //版本
    @Column(name = "create_time")
    private Date createTime; //创建时间
    @Column(name = "update_time")
    private Date updateTime; //更新时间
    private String uuid; //uuid
    private Integer uid; //用户ID
    private Integer pid; //理财产品ID
    @Column(name = "invest_amount")
    private BigDecimal investAmount; //购买金额
    private BigDecimal proearn; //预期收益
    private BigDecimal realearn; //实际收益
    @Column(name = "vip_interestrate")
    private BigDecimal vipInterestrate; //vip利率

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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public BigDecimal getProearn() {
        return proearn;
    }

    public void setProearn(BigDecimal proearn) {
        this.proearn = proearn;
    }

    public BigDecimal getRealearn() {
        return realearn;
    }

    public void setRealearn(BigDecimal realearn) {
        this.realearn = realearn;
    }

    public BigDecimal getVipInterestrate() {
        return vipInterestrate;
    }

    public void setVipInterestrate(BigDecimal vipInterestrate) {
        this.vipInterestrate = vipInterestrate;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
