package com.guwr.accumulate.facade.user.entity;

import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gwr
 * Description 产品投资总额
 * Path com.guwr.accumulate.facade.wmps.entity.UserProductInvest
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_user_wmps_invest")
public class UserProductInvest implements Serializable {


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
    private Integer uid;         //用户ID
    @Column(name = "total_invest")
    private BigDecimal totalInvest; //投资总金额
    @Column(name = "user_type")
    private Integer userType; //用户类型

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

    public BigDecimal getTotalInvest() {
        return totalInvest;
    }

    public void setTotalInvest(BigDecimal totalInvest) {
        this.totalInvest = totalInvest;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
