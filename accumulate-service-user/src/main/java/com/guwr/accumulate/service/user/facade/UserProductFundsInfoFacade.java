package com.guwr.accumulate.service.user.facade;

import com.guwr.accumulate.facade.user.entity.UserProductFundsInfo;
import com.guwr.accumulate.facade.user.facade.IUserProductFundsInfoFacade;
import com.guwr.accumulate.service.user.core.service.IUserProductFundsInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.user.facade.UserProductFundsInfoFacade
 * Date 2016/8/21
 * Time 18:31
 */
@Component
public class UserProductFundsInfoFacade implements IUserProductFundsInfoFacade {

    private static Logger logger = LoggerFactory.getLogger(UserProductFundsInfoFacade.class);
    @Autowired
    private IUserProductFundsInfoService userProductFundsInfoService;

    @Override
    public List<UserProductFundsInfo> saveUserProductFundsInfos(List<UserProductFundsInfo> entitys) {
        logger.info("saveUserProductFundsInfos.entitys = [" + entitys + "]");
        return userProductFundsInfoService.saveUserProductFundsInfos(entitys);
    }
}
