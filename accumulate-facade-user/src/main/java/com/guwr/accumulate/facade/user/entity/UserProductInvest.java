package com.guwr.accumulate.facade.user.entity;

import com.guwr.accumulate.common.entity.BaseEntity;
import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by gwr
 * Description 产品投资总额
 * Path com.guwr.accumulate.facade.wmps.entity.UserProductInvest
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_user_wmps_invest")
public class UserProductInvest extends BaseEntity {


    private static final long serialVersionUID = -1309600883062868382L;

    private Integer uid;         //用户ID
    @Column(name = "total_invest")
    private BigDecimal totalInvest; //投资总金额
    @Column(name = "user_type")
    private Integer userType; //用户类型

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
