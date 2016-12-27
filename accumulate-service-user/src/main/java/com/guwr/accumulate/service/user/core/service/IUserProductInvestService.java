package com.guwr.accumulate.service.user.core.service;


import com.guwr.accumulate.facade.user.entity.UserProductInvest;
import com.guwr.accumulate.facade.user.vo.UserProductInvestVO;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.service.IUserProductInvestService
 * Date 2016/8/13
 * Time 21:07
 */
public interface IUserProductInvestService {
    UserProductInvest save(UserProductInvest entity);

    UserProductInvest update(UserProductInvest entity);

    UserProductInvest findOne(Integer id);

    /**
     * 产品投资总额
     *
     * @param uid
     * @return
     */
    UserProductInvest findOneByUid(Integer uid);

    BigDecimal changeInInvest(UserProductInvestVO info);

    BigDecimal changeOutInvest(UserProductInvestVO info);

    /**
     * 修改投资总额
     *
     * @param userProductInvest
     */
    void changeInInvest(UserProductInvest userProductInvest, BigDecimal invest, BigDecimal afterTotalInvest, String uuid);
}