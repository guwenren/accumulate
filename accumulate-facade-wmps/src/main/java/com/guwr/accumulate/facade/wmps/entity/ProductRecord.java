package com.guwr.accumulate.facade.wmps.entity;

import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.wmps.entity.ProductRecord
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_wmps_product_record")
public class ProductRecord implements Serializable {


    private static final long serialVersionUID = -1309600883062868382L;
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
    private Integer uid;
    private Integer pid;
    @Column(name = "invest_amount")
    private BigDecimal investAmount;
    @Column(name = "effect_amount")
    private BigDecimal effectAmount;
    private BigDecimal proearn;//预期收益
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
