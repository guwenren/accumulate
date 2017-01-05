package com.guwr.accumulate.service.user.facade;

import com.guwr.accumulate.facade.user.entity.UserProductInvest;
import com.guwr.accumulate.facade.user.facade.IUserProductInvestFacade;
import com.guwr.accumulate.service.user.core.service.IUserProductInvestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.user.facade.UserProductInvestFacade
 * Date 2016/8/21
 * Time 18:31
 */
@Component
public class UserProductInvestFacade implements IUserProductInvestFacade {

    private static Logger logger = LoggerFactory.getLogger(UserProductInvestFacade.class);

    @Autowired
    private IUserProductInvestService userProductInvestService;

    @Override
    public UserProductInvest findUserProductInvestByUid(Integer uid) {
        UserProductInvest userProductInvest = userProductInvestService.findOneByUid(uid);
        return userProductInvest;
    }
}
