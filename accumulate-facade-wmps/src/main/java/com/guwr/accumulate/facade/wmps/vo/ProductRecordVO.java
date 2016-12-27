package com.guwr.accumulate.facade.wmps.vo;

import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.common.web.vo.BaseVO;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description  购买vo
 * Path com.guwr.accumulate.facade.wmps.vo.ProductRecordVO
 * Date 2016/9/8
 * Time 16:57
 */
public class ProductRecordVO extends BaseVO {


    private static final long serialVersionUID = -1724877001041720753L;

    private Integer uid; //用户ID
    private Integer pid; //产品ID
    private BigDecimal investAmount; //投资金额
    private BigDecimal proearn;//预期收益
    private BigDecimal interestrate; //vip利率
    private String consumerQueue;

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

    public BigDecimal getProearn() {
        return proearn;
    }

    public void setProearn(BigDecimal proearn) {
        this.proearn = proearn;
    }

    public BigDecimal getInterestrate() {
        return interestrate;
    }

    public void setInterestrate(BigDecimal interestrate) {
        this.interestrate = interestrate;
    }

    public String getConsumerQueue() {
        return consumerQueue;
    }

    public void setConsumerQueue(String consumerQueue) {
        this.consumerQueue = consumerQueue;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
