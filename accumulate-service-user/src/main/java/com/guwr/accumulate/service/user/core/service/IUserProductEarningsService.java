package com.guwr.accumulate.service.user.core.service;


import com.guwr.accumulate.facade.user.entity.UserProductEarnings;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

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

    @Transactional(rollbackFor = Exception.class)
    void saveOrUpdateUserProductEarnings(Date date, Integer uid, String uuid, BigDecimal invest, Integer pid, BigDecimal vipInterestrate, BigDecimal proearn, BigDecimal interestrate, Integer phases);
}
