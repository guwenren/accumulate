package com.guwr.accumulate.facade.account.vo;

import com.guwr.accumulate.common.web.vo.BaseVO;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.account.vo.AccountBalanceVO
 * Date 2016/9/12
 * Time 15:54
 */
public class AccountBalanceVO extends BaseVO {


    private static final long serialVersionUID = -3118245153258642243L;
    private Integer id;
    private Integer uid;
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
