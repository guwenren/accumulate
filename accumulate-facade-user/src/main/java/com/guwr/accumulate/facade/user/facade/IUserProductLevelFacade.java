package com.guwr.accumulate.facade.user.facade;


import com.guwr.accumulate.facade.user.entity.UserProductLevel;
import com.guwr.accumulate.facade.user.vo.UserProductLevelVO;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.user.facade.IUserProductLevelFacade
 * Date 2016/8/21
 * Time 18:33
 */
public interface IUserProductLevelFacade {
     UserProductLevel findUserProductLevelByIn(UserProductLevelVO info);
}
