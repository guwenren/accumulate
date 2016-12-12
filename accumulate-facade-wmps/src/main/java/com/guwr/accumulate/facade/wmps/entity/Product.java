package com.guwr.accumulate.facade.wmps.entity;

import com.guwr.accumulate.common.entity.BaseEntity;
import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.wmps.entity.Product
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_wmps_product")
public class Product extends BaseEntity {


    private static final long serialVersionUID = -1309600883062868382L;

    private BigDecimal amount; //募集金额
    @Column(name = "invest_amount")
    private BigDecimal investAmount; //投资金额
    @Column(name = "effect_amount")
    private BigDecimal effectAmount; //有效金额

    private Integer status; //状态:0尚未发布 1.已发布，认购中 2认购完成，审核中 3计息中 4到期完成
    private Integer phases; //期限
    private BigDecimal interestrate; //发行年利率

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public BigDecimal getEffectAmount() {
        return effectAmount;
    }

    public void setEffectAmount(BigDecimal effectAmount) {
        this.effectAmount = effectAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPhases() {
        return phases;
    }

    public void setPhases(Integer phases) {
        this.phases = phases;
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
