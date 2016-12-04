package com.guwr.accumulate.service.user.core.service;


import com.guwr.accumulate.facade.user.entity.UserProductLevel;
import com.guwr.accumulate.facade.user.vo.UserProductLevelVO;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.service.IUserProductLevelService
 * Date 2016/8/13
 * Time 21:07
 */
public interface IUserProductLevelService {

    UserProductLevel save(UserProductLevel entity);

    /**
     * 根据金额计算等级
     *
     * @param invest
     * @return
     */
    UserProductLevel findOneByInvest(BigDecimal invest);

    /**
     *
     * @param info
     * @return
     */
    UserProductLevel findUserProductLevelByIn(UserProductLevelVO info);

    /**
     *
     * @param info
     * @return
     */
    UserProductLevel findUserProductLevelByOut(UserProductLevelVO info);
}