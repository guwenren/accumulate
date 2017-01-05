package com.guwr.accumulate.facade.user.facade;


import com.guwr.accumulate.facade.user.entity.UserProductInvest;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.user.facade.IUserProductInvestFacade
 * Date 2016/8/21
 * Time 18:33
 */
public interface IUserProductInvestFacade {


    UserProductInvest findUserProductInvestByUid(Integer uid);
}
