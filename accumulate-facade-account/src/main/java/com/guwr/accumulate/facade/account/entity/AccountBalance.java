package com.guwr.accumulate.facade.account.entity;

import com.guwr.accumulate.common.entity.BaseEntity;
import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.account.entity.AccountBalance
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_account_balance")
@javax.persistence.Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AccountBalance extends BaseEntity {


    private static final long serialVersionUID = -1309600883062868382L;

    private Integer uid; //用户ID
    private BigDecimal balance; //余额

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
