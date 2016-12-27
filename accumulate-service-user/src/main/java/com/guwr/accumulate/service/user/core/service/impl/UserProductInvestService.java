package com.guwr.accumulate.service.user.core.service.impl;


import com.guwr.accumulate.common.util.CommonUtils;
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
        logger.info("save.entity = [" + CommonUtils.obj2Json(entity) + "]");
        return repository.save(entity);
    }

    @Override
    public UserProductInvest update(UserProductInvest entity) {
        logger.info("update.entity = [" + CommonUtils.obj2Json(entity) + "]");
        return repository.save(entity);
    }

    @Override
    public UserProductInvest findOne(Integer id) {
        logger.info("id = [" + id + "]");
        return repository.findOne(id);
    }

    @Override
    public UserProductInvest findOneByUid(Integer uid) {
        logger.info("findOneByUid.uid = " + uid);
        return repository.findOneByUid(uid);
    }

    @Override
    public BigDecimal changeInInvest(UserProductInvestVO info) {
        logger.info("changeInInvest.info = [" + info + "]");
        Integer uid = info.getUid();
        logger.info("{},查询投资总额", uid);
        BigDecimal invest = info.getInvest();
        String uuid = info.getUuid();
        BigDecimal beforTotalInvest; //用户变更前投资金额
        BigDecimal afterTotalInvest; //用户变更后投资金额 根据此金额计算当前用户VIP等级
        //当前投资总额
        Date date = new Date();
        UserProductInvest userProductInvest = repository.findOneByUid(uid);
        logger.info("changeInInvest.userProductInvest = " + CommonUtils.obj2Json(userProductInvest));
        if (userProductInvest == null) {
            beforTotalInvest = BigDecimal.ZERO;
            afterTotalInvest = invest;
            userProductInvest = new UserProductInvest();
            userProductInvest.setUuid(uuid);
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
        logger.info("{}_插入变更记录", uid);
        return afterTotalInvest;
    }

    @Override
    public BigDecimal changeOutInvest(UserProductInvestVO info) {
        return null;
    }

    @Override
    public void changeInInvest(UserProductInvest userProductInvest, BigDecimal invest, BigDecimal afterTotalInvest, String uuid) {
        save(userProductInvest);
        // 添加投资总额变更记录
        UserProductInvestLog entity = new UserProductInvestLog();
        entity.setPid(userProductInvest.getId());
        entity.setAfterTotalInvest(afterTotalInvest);
        entity.setBeforTotalInvest(userProductInvest.getTotalInvest());
        entity.setCreateTime(userProductInvest.getUpdateTime());
        entity.setUpdateTime(userProductInvest.getUpdateTime());
        entity.setUuid(uuid);
        entity.setInvestMoney(invest);
        entity.setInvestType(UserProductInvestLogInvestType.IN.getValue());
        // 添加变更记录
        userProductInvestLogService.save(entity);
        logger.info("{}_插入变更记录", userProductInvest.getUid());
    }
}
