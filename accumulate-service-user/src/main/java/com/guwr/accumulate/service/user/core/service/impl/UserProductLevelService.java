package com.guwr.accumulate.service.user.core.service.impl;


import com.guwr.accumulate.facade.user.entity.UserProductLevel;
import com.guwr.accumulate.facade.user.vo.UserProductInvestVO;
import com.guwr.accumulate.facade.user.vo.UserProductLevelVO;
import com.guwr.accumulate.service.user.core.dao.UserProductLevelRepository;
import com.guwr.accumulate.service.user.core.service.IUserProductInvestService;
import com.guwr.accumulate.service.user.core.service.IUserProductLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.user.service.impl.UserProductLevelService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class UserProductLevelService implements IUserProductLevelService {

    private static Logger logger = LoggerFactory.getLogger(UserProductLevelService.class);

    @Autowired
    private UserProductLevelRepository repository;

    @Autowired
    private IUserProductInvestService userProductInvestService;

    @Override
    public UserProductLevel save(UserProductLevel entity) {
        return repository.save(entity);
    }

    @Override
    public UserProductLevel findOneByInvest(BigDecimal invest) {
        return repository.findOneByInvest(invest);
    }

    @Override
    public UserProductLevel findUserProductLevelByIn(UserProductLevelVO info) {
        logger.info("UserProductLevelService.findUserProductLevelByIn.info = [" + info + "]");
        UserProductLevel userProductLevel;

        UserProductInvestVO userProductInvestVO = new UserProductInvestVO();
        userProductInvestVO.setInvest(info.getInvest());
        userProductInvestVO.setUid(info.getUid());

        BigDecimal afterTotalInvest = userProductInvestService.changeInInvest(userProductInvestVO);
        userProductLevel = this.findOneByInvest(afterTotalInvest);
        return userProductLevel;
    }

    @Override
    public UserProductLevel findUserProductLevelByOut(UserProductLevelVO info) {
        UserProductLevel userProductLevel = null;
        return userProductLevel;
    }
}
