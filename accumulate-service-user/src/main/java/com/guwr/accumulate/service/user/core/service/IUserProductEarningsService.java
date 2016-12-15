package com.guwr.accumulate.service.user.core.service;


import com.guwr.accumulate.facade.user.entity.UserProductEarnings;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.service.IUserProductEarningsService
 * Date 2016/8/13
 * Time 21:07
 */
public interface IUserProductEarningsService {
    UserProductEarnings save(UserProductEarnings entity);

    UserProductEarnings update(UserProductEarnings entity);

    UserProductEarnings findOneByUidPidInterestrate(Integer uid, Integer pid, BigDecimal interestrate);
}
