package com.guwr.accumulate.service.user.facade;

import com.guwr.accumulate.facade.user.entity.UserProductLevel;
import com.guwr.accumulate.facade.user.facade.IUserProductLevelFacade;
import com.guwr.accumulate.facade.user.vo.UserProductLevelVO;
import com.guwr.accumulate.service.user.core.service.IUserProductLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.user.facade.UserProductLevelFacade
 * Date 2016/8/21
 * Time 18:31
 */
@Component
public class UserProductLevelFacade implements IUserProductLevelFacade {

    private static Logger logger = LoggerFactory.getLogger(UserProductLevelFacade.class);

    @Autowired
    private IUserProductLevelService userProductLevelService;

    @Override
    public UserProductLevel findUserProductLevelByIn(UserProductLevelVO info) {
        logger.info("findUserProductLevelByIn.info = [" + info + "]");
        return userProductLevelService.findUserProductLevelByIn(info);
    }
}
