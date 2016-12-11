package com.guwr.accumulate.facade.user.entity;

import com.guwr.accumulate.common.entity.BaseEntity;
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
public class UserProductInvestLog extends BaseEntity {


    private static final long serialVersionUID = -1309600883062868382L;

    private Integer pid;//产品投资总额ID
    @Column(name = "invest_money")
    private BigDecimal investMoney;// 变更金额
    @Column(name = "invest_type")
    private Integer investType;//变更方向: 1:增加，2:减少 （默认1）
    @Column(name = "befor_total_invest")
    private BigDecimal beforTotalInvest;//用户变更前在投资金额
    @Column(name = "after_total_invest")
    private BigDecimal afterTotalInvest;//用户变更后在投资金额

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
