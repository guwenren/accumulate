package com.guwr.accumulate.facade.user.facade;


import com.guwr.accumulate.facade.user.entity.UserProductEarnings;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.user.facade.IUserProductEarningsFacade
 * Date 2016/8/21
 * Time 18:33
 */
public interface IUserProductEarningsFacade {

    UserProductEarnings findOneByUidPidInterestrate(Integer uid, Integer pid, BigDecimal interestrate);

    void save(UserProductEarnings userProductEarnings);

    void update(UserProductEarnings userProductEarnings);

}
