package com.guwr.accumulate.facade.user.facade;

import com.guwr.accumulate.facade.user.entity.UserProductFundsInfo;

import java.util.List;

/**
 * Created by   guwr
 * Project_name accumulate
 * Path         com.guwr.accumulate.facade.user.facade.IUserProductFundsInfoFacade
 * Date         2016/12/20
 * Time         17:57
 * Description
 */
public interface IUserProductFundsInfoFacade {
    List<UserProductFundsInfo> saveUserProductFundsInfos(List<UserProductFundsInfo> entitys);

    void saveEntity(List<UserProductFundsInfo> entitys);
}
