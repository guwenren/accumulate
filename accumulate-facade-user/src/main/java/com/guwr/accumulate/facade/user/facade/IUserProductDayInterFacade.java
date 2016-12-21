package com.guwr.accumulate.facade.user.facade;

import com.guwr.accumulate.facade.user.entity.UserProductDayInter;

import java.util.List;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.facade.user.facade.IUserProductDayInterFacade
 * Date         2016/12/20
 * Time         17:57
 * Description
 */
public interface IUserProductDayInterFacade {

    List<UserProductDayInter> saveUserProductDayInters(List<UserProductDayInter> entitys);
}
