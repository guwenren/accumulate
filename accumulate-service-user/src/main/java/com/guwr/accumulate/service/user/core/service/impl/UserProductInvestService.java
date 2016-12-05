package com.guwr.accumulate.service.user.core.service.impl;


import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.common.util.StringUtils;
import com.guwr.accumulate.facade.user.entity.UserProductEarnings;
import com.guwr.accumulate.facade.user.entity.UserProductInvest;
import com.guwr.accumulate.facade.user.entity.UserProductInvestLog;
import com.guwr.accumulate.facade.user.enums.UserProductInvestLogInvestType;
import com.guwr.accumulate.facade.user.enums.UserProductInvestUserType;
import com.guwr.accumulate.facade.user.vo.UserProductInvestVO;
import com.guwr.accumulate.service.user.core.dao.UserProductInvestRepository;
import com.guwr.accumulate.service.user.core.service.IUserProductEarningsService;
import com.guwr.accumulate.service.user.core.service.IUserProductInvestLogService;
import com.guwr.accumulate.service.user.core.service.IUserProductInvestService;
import com.guwr.accumulate.service.user.core.service.IUserProductLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.user.service.impl.UserProductInvestService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class UserProductInvestService implements IUserProductInvestService {

    private static Logger logger = LoggerFactory.getLogger(UserProductInvestService.class);
    @Autowired
    private UserProductInvestRepository repository;
    @Autowired
    private IUserProductLevelService userProductLevelService;
    @Autowired
    private IUserProductEarningsService userProductEarningsService;

    @Autowired
    private IUserProductInvestLogService userProductInvestLogService;

    @Override
    public UserProductInvest save(UserProductInvest entity) {
        return repository.save(entity);
    }

    @Override
    public UserProductInvest update(UserProductInvest entity) {
        return repository.save(entity);
    }

    @Override
    public UserProductInvest findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public BigDecimal changeInInvest(UserProductInvestVO info) {
        logger.info("UserProductInvestService.changeInInvest.info = [" + info + "]");
        Integer uid = info.getUid();
        BigDecimal invest = info.getInvest();
        BigDecimal beforTotalInvest; //用户变更前在投资金额
        BigDecimal afterTotalInvest; //用户变更后在投资金额 根据此金额计算当前用户VIP等级

        //当前投资总额
        Date date = new Date();
        String uuid = StringUtils.getUUID();
        UserProductInvest userProductInvest = repository.findOneByUid(uid);
        logger.info("UserProductInvestService.changeInInvest.userProductInvest = " + CommonUtils.obj2Json(userProductInvest));
        if (userProductInvest == null) {
            beforTotalInvest = BigDecimal.ZERO;
            afterTotalInvest = invest;
            userProductInvest = new UserProductInvest();
            userProductInvest.setUid(uid);
            userProductInvest.setTotalInvest(afterTotalInvest);
            userProductInvest.setCreateTime(date);
            userProductInvest.setUpdateTime(date);
            userProductInvest.setUserType(UserProductInvestUserType.USERTYPE_NORMAL.getValue());
            userProductInvest = save(userProductInvest);
        } else {
            beforTotalInvest = userProductInvest.getTotalInvest();
            afterTotalInvest = beforTotalInvest.add(invest);
            userProductInvest.setTotalInvest(afterTotalInvest);
            userProductInvest.setUpdateTime(date);
            userProductInvest = update(userProductInvest);
        }
        // 添加投资总额变更记录
        UserProductInvestLog entity = new UserProductInvestLog();
        entity.setPid(userProductInvest.getId());
        entity.setAfterTotalInvest(afterTotalInvest);
        entity.setBeforTotalInvest(beforTotalInvest);
        entity.setCreateTime(date);
        entity.setUpdateTime(date);
        entity.setUuid(uuid);
        entity.setInvestMoney(invest);
        entity.setInvestType(UserProductInvestLogInvestType.IN.getValue());
        // 添加变更记录
        userProductInvestLogService.save(entity);
        return afterTotalInvest;
    }

    @Override
    public BigDecimal changeOutInvest(UserProductInvestVO info) {
        return null;
    }
}