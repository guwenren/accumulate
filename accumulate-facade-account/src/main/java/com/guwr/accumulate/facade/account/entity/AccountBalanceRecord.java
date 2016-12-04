package com.guwr.accumulate.facade.account.entity;

import com.guwr.accumulate.common.util.CommonUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.account.entity.AccountBalanceRecord
 * Date 2016/8/21
 * Time 14:32
 */
@Entity
@Table(name = "tbl_account_balance_record")
public class AccountBalanceRecord implements Serializable {


    private static final long serialVersionUID = -1309600883062868382L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer uid;//用户ID
    private Integer type;//资金流水类型
    private BigDecimal amount;//交易金额
    private BigDecimal balance;//余额
    private String description;//描述

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
