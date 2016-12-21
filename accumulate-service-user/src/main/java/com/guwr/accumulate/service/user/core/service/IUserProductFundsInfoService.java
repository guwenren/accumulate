package com.guwr.accumulate.service.user.core.service;


import com.guwr.accumulate.facade.user.entity.UserProductFundsInfo;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.service.IUserProductFundsInfoService
 * Date 2016/8/13
 * Time 21:07
 */
public interface IUserProductFundsInfoService {

    List<UserProductFundsInfo> saveUserProductFundsInfos(List<UserProductFundsInfo> entitys);
}
