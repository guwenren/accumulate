package com.guwr.accumulate.facade.user.vo;

import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.common.web.vo.BaseVO;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.user.vo.UserProductLevelVO
 * Date 2016/9/12
 * Time 15:31
 */
public class UserProductLevelVO extends BaseVO {

    private static final long serialVersionUID = 2392434906231215388L;
    private Integer uid; //用户ID
    private Integer pid; //产品ID
    private BigDecimal invest; //变动金额
    private Integer phases; //期限
    private BigDecimal interestrate; //发行年利率
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

    public BigDecimal getInvest() {
        return invest;
    }

    public void setInvest(BigDecimal invest) {
        this.invest = invest;
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
