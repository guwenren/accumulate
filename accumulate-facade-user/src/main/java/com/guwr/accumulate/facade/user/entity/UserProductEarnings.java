package com.guwr.accumulate.facade.user.entity;

import com.guwr.accumulate.common.entity.BaseEntity;
import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by gwr
 * Description  用户投资收益表
 * Path com.guwr.accumulate.facade.wmps.entity.UserProductEarnings
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_user_wmps_earnings")
public class UserProductEarnings extends BaseEntity {


    private static final long serialVersionUID = 7057799382653381564L;

    private Integer uid; //用户ID
    private Integer pid; //理财产品ID
    @Column(name = "invest_amount")
    private BigDecimal investAmount; //购买金额
    private BigDecimal proearn; //预期收益
    private BigDecimal realearn; //实际收益
    private BigDecimal interestrate; //vip利率

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

    public BigDecimal getInterestrate() {
        return interestrate;
    }

    public void setInterestrate(BigDecimal interestrate) {
        this.interestrate = interestrate;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
