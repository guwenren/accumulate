package com.guwr.accumulate.service.user.core.service;


import com.guwr.accumulate.facade.user.entity.UserProductInvest;
import com.guwr.accumulate.facade.user.entity.UserProductLevel;
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

    BigDecimal changeInInvest(UserProductInvestVO info);

    BigDecimal changeOutInvest(UserProductInvestVO info);
}