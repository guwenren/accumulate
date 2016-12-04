package com.guwr.accumulate.facade.account.vo;

import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.common.web.vo.BaseVO;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.account.vo.AccountBalanceRecordVO
 * Date 2016/9/12
 * Time 15:54
 */
public class AccountBalanceRecordVO extends BaseVO {


    private static final long serialVersionUID = -3118245153258642243L;
    private Integer uid;
    private Integer type;
    private BigDecimal amount;
    private BigDecimal balance;
    private String description;

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
