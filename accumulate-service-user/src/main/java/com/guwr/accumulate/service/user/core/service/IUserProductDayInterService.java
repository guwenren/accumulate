package com.guwr.accumulate.service.user.core.service;


import com.guwr.accumulate.facade.user.entity.UserProductDayInter;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.service.IUserProductDayInterService
 * Date 2016/8/13
 * Time 21:07
 */
public interface IUserProductDayInterService {
    UserProductDayInter saveUserProductDayInter(UserProductDayInter entity);

    List<UserProductDayInter> saveUserProductDayInters(List<UserProductDayInter> entitys);

    UserProductDayInter editUserProductDayInter(UserProductDayInter entity);
}
