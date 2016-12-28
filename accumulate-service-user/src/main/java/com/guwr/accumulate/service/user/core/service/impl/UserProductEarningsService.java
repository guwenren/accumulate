package com.guwr.accumulate.service.user.core.service.impl;


import com.guwr.accumulate.facade.user.entity.UserProductEarnings;
import com.guwr.accumulate.service.user.core.dao.UserProductEarningsRepository;
import com.guwr.accumulate.service.user.core.service.IUserProductEarningsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.service.impl.UserProductEarningsService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class UserProductEarningsService implements IUserProductEarningsService {


    private static Logger logger = LoggerFactory.getLogger(UserProductEarningsService.class);

    @Autowired
    private UserProductEarningsRepository repository;


    @Override
    public UserProductEarnings save(UserProductEarnings entity) {
        return repository.save(entity);
    }

    @Override
    public UserProductEarnings update(UserProductEarnings entity) {
        return repository.save(entity);
    }

    @Override
    public UserProductEarnings findOneByUidPidInterestrate(Integer uid, Integer pid, BigDecimal interestrate) {
        return repository.findOneByUidPidInterestrate(uid, pid, interestrate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdateUserProductEarnings(Date date, Integer uid, String uuid, BigDecimal invest, Integer pid, BigDecimal vipInterestrate, BigDecimal proearn) {
        logger.info("date = [" + date + "], uid = [" + uid + "], uuid = [" + uuid + "], invest = [" + invest + "], pid = [" + pid + "], vipInterestrate = [" + vipInterestrate + "], proearn = [" + proearn + "]");
        UserProductEarnings userProductEarnings = findOneByUidPidInterestrate(uid, pid, vipInterestrate);
        logger.info("saveOrUpdateUserProductEarnings = " + userProductEarnings);
        if (userProductEarnings == null) {
            userProductEarnings = new UserProductEarnings();
            userProductEarnings.setInvestAmount(invest);
            userProductEarnings.setCreateTime(date);
            userProductEarnings.setUpdateTime(date);
            userProductEarnings.setUid(uid);
            userProductEarnings.setPid(pid);
            userProductEarnings.setInterestrate(vipInterestrate);
            userProductEarnings.setProearn(proearn);
            userProductEarnings.setUuid(uuid);
            userProductEarnings.setRealearn(BigDecimal.ZERO);
            save(userProductEarnings);
        } else {
            BigDecimal sumAmount = userProductEarnings.getInvestAmount().add(invest);
            userProductEarnings.setInvestAmount(sumAmount);
            userProductEarnings.setProearn(proearn);
            userProductEarnings.setUpdateTime(date);
            update(userProductEarnings);
        }
    }
}
