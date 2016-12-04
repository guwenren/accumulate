package com.guwr.accumulate.service.user.facade;

import com.guwr.accumulate.facade.user.entity.UserProductEarnings;
import com.guwr.accumulate.facade.user.facade.IUserProductEarningsFacade;
import com.guwr.accumulate.service.user.core.service.IUserProductEarningsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.user.facade.UserProductEarningsFacade
 * Date 2016/8/21
 * Time 18:31
 */
@Component
public class UserProductEarningsFacade implements IUserProductEarningsFacade {

    private static Logger logger = LoggerFactory.getLogger(UserProductEarningsFacade.class);

    @Autowired
    private IUserProductEarningsService userProductEarningsService;


    @Override
    public UserProductEarnings findOneByUidPidLid(Integer uid, Integer pid, Integer lid) {
        logger.info("UserProductEarningsFacade.findOneByUidPidLid.uid = [" + uid + "], pid = [" + pid + "], lid = [" + lid + "]");
        return userProductEarningsService.findOneByUidPidLid(uid, pid, lid);
    }

    @Override
    public void save(UserProductEarnings userProductEarnings) {
        logger.info("UserProductEarningsFacade.save.userProductEarnings = [" + userProductEarnings + "]");
        userProductEarningsService.save(userProductEarnings);
    }

    @Override
    public void update(UserProductEarnings userProductEarnings) {
        logger.info("UserProductEarningsFacade.update.userProductEarnings = [" + userProductEarnings + "]");
        userProductEarningsService.update(userProductEarnings);
    }
}
