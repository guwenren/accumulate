package com.guwr.accumulate.facade.user.entity;

import com.guwr.accumulate.common.entity.BaseEntity;
import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gwr
 * Description 产品投资总额级别
 * Path com.guwr.accumulate.facade.wmps.entity.UserProductLevel
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_user_wmps_level")
public class UserProductLevel extends BaseEntity {


    private static final long serialVersionUID = -1309600883062868382L;
    private Integer level;         //等级
    @Column(name = "min_invest")
    private BigDecimal minInvest; //最小值
    @Column(name = "max_invest")
    private BigDecimal maxInvest; //最大值
    private BigDecimal interestrate; //利率


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigDecimal getMinInvest() {
        return minInvest;
    }

    public void setMinInvest(BigDecimal minInvest) {
        this.minInvest = minInvest;
    }

    public BigDecimal getMaxInvest() {
        return maxInvest;
    }

    public void setMaxInvest(BigDecimal maxInvest) {
        this.maxInvest = maxInvest;
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
