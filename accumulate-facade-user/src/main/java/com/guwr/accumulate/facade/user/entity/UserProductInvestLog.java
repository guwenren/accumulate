package com.guwr.accumulate.facade.user.entity;

import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gwr
 * Description 产品投资总额日志
 * Path com.guwr.accumulate.facade.wmps.entity.UserProductInvestLog
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_user_wmps_invest_log")
public class UserProductInvestLog implements Serializable {


    private static final long serialVersionUID = -1309600883062868382L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "create_time")
    private Date createTime; //创建时间
    @Column(name = "update_time")
    private Date updateTime; //更新时间

    private String uuid;// uuid
    private Integer pid;//产品投资总额ID
    @Column(name = "invest_money")
    private BigDecimal investMoney;// 变更金额
    @Column(name = "invest_type")
    private Integer investType;//变更方向: 1:增加，2:减少 （默认1）
    @Column(name = "befor_total_invest")
    private BigDecimal beforTotalInvest;//用户变更前在投资金额
    @Column(name = "after_total_invest")
    private BigDecimal afterTotalInvest;//用户变更后在投资金额

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public BigDecimal getInvestMoney() {
        return investMoney;
    }

    public void setInvestMoney(BigDecimal investMoney) {
        this.investMoney = investMoney;
    }

    public Integer getInvestType() {
        return investType;
    }

    public void setInvestType(Integer investType) {
        this.investType = investType;
    }

    public BigDecimal getBeforTotalInvest() {
        return beforTotalInvest;
    }

    public void setBeforTotalInvest(BigDecimal beforTotalInvest) {
        this.beforTotalInvest = beforTotalInvest;
    }

    public BigDecimal getAfterTotalInvest() {
        return afterTotalInvest;
    }

    public void setAfterTotalInvest(BigDecimal afterTotalInvest) {
        this.afterTotalInvest = afterTotalInvest;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
