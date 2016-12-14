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
 * Path com.guwr.accumulate.facade.wmps.entity.ProductRecord
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_wmps_product_record")
public class ProductRecord extends BaseEntity {


    private static final long serialVersionUID = -1309600883062868382L;

    private Integer uid;
    private Integer pid;
    @Column(name = "invest_amount")
    private BigDecimal investAmount;
    @Column(name = "effect_amount")
    private BigDecimal effectAmount;
    private BigDecimal proearn;//预期收益
    @Column(name = "vip_interestrate")
    private BigDecimal vipInterestrate; //vip利率
    private Integer status; //状态

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

    public BigDecimal getEffectAmount() {
        return effectAmount;
    }

    public void setEffectAmount(BigDecimal effectAmount) {
        this.effectAmount = effectAmount;
    }

    public BigDecimal getProearn() {
        return proearn;
    }

    public void setProearn(BigDecimal proearn) {
        this.proearn = proearn;
    }

    public BigDecimal getVipInterestrate() {
        return vipInterestrate;
    }

    public void setVipInterestrate(BigDecimal vipInterestrate) {
        this.vipInterestrate = vipInterestrate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
