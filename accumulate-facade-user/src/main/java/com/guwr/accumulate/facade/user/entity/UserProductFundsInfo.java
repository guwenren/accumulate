package com.guwr.accumulate.facade.user.entity;

import com.guwr.accumulate.common.entity.BaseEntity;
import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by gwr
 * Description  每日发息资金流水
 * Path com.guwr.accumulate.facade.wmps.entity.UserProductFundsInfo
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_user_wmps_funds_info")
public class UserProductFundsInfo extends BaseEntity {


    private static final long serialVersionUID = 7057799382653381564L;

    private Integer uid; //用户ID
    private BigDecimal inter; //利息
    private Integer bid; //购买记录ID
    private Integer pid; //产品ID
    private Integer sid; //发息总表ID
    private BigDecimal interestrate; //利率

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public BigDecimal getInter() {
        return inter;
    }

    public void setInter(BigDecimal inter) {
        this.inter = inter;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
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
