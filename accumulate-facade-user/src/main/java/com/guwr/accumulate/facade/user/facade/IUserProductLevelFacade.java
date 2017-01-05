package com.guwr.accumulate.facade.user.facade;


import com.guwr.accumulate.facade.user.entity.UserProductLevel;
import com.guwr.accumulate.facade.user.vo.UserProductLevelVO;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.user.facade.IUserProductLevelFacade
 * Date 2016/8/21
 * Time 18:33
 */
public interface IUserProductLevelFacade {
     void findUserProductLevelByIn(UserProductLevelVO info);

    UserProductLevel findUserProductLevelByInvest(BigDecimal invest);
}
