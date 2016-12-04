package com.guwr.accumulate.service.user.core.service.impl;


import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.facade.user.entity.UserProductInvestLog;
import com.guwr.accumulate.service.user.core.dao.UserProductInvestLogRepository;
import com.guwr.accumulate.service.user.core.service.IUserProductInvestLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.user.service.impl.UserProductInvestLogService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class UserProductInvestLogService implements IUserProductInvestLogService {

    private static Logger logger = LoggerFactory.getLogger(UserProductInvestLogService.class);
    @Autowired
    private UserProductInvestLogRepository repository;

    @Override
    public UserProductInvestLog save(UserProductInvestLog entity) {
        logger.info("UserProductInvestLogService.save.entity = [" + CommonUtils.obj2Json(entity) + "]");
        return repository.save(entity);
    }
}
