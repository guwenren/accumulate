package com.guwr.accumulate.facade.user.vo;

import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.common.web.vo.BaseVO;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.user.vo.UserProductInvestVO
 * Date 2016/9/12
 * Time 15:31
 */
public class UserProductInvestVO extends BaseVO {

    private static final long serialVersionUID = 2392434906231215388L;
    private Integer uid; //用户ID
    private BigDecimal invest; //变动金额


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public BigDecimal getInvest() {
        return invest;
    }

    public void setInvest(BigDecimal invest) {
        this.invest = invest;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
