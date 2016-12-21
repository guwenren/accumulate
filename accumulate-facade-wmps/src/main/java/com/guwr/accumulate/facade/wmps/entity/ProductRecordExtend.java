package com.guwr.accumulate.facade.wmps.entity;

import com.guwr.accumulate.common.util.AmountUtils;
import com.guwr.accumulate.common.util.CommonUtils;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.wmps.entity.ProductRecordExtend
 * Date 2016/8/21
 * Time 14:32
 */
public class ProductRecordExtend extends ProductRecord {

    private static final Integer YEAR = 365;

    private static final long serialVersionUID = -1309600883062868382L;

    private BigDecimal pinterestrate;//产品年利率

    public BigDecimal getPinterestrate() {
        return pinterestrate;
    }

    public void setPinterestrate(BigDecimal pinterestrate) {
        this.pinterestrate = pinterestrate;
    }

    /**
     * 每天利息
     *
     * @return
     */
    public BigDecimal getInter() {
        //   CAST(a.effectiveamount*(b.interestrate+IFNULL(a.vipratio,0))/365 AS DECIMAL(10,2)) as income  计算公式
        BigDecimal inter;
        if (getInterestrate() == null) {
            setInterestrate(BigDecimal.ZERO); // vip利率
        }
        if (getPinterestrate() == null) {
            setPinterestrate(BigDecimal.ZERO); // 产品利率
        }
        BigDecimal effectAmount = getEffectAmount();//投资金额
        BigDecimal interestrate = getInterestrate().add(getPinterestrate());

        BigDecimal multiplyInter = AmountUtils.round(effectAmount).multiply(AmountUtils.round(interestrate));
        System.out.println("multiplyInter = " + multiplyInter);
        inter = AmountUtils.div(multiplyInter, new BigDecimal(YEAR));
        System.out.println("inter = " + inter);
        return inter;
    }

    @Override
    public String toString() {
        return CommonUtils.obj2Json(this);
    }
}
