package com.guwr.accumulate.service.user.core.service.impl;


import com.guwr.accumulate.facade.user.entity.UserProductFundsInfo;
import com.guwr.accumulate.service.user.core.dao.UserProductFundsInfoRepository;
import com.guwr.accumulate.service.user.core.service.IUserProductFundsInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.user.service.impl.UserProductFundsInfoService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class UserProductFundsInfoService implements IUserProductFundsInfoService {

    private static Logger logger = LoggerFactory.getLogger(UserProductFundsInfoService.class);

    @Autowired
    private UserProductFundsInfoRepository repository;

    @Override
    public List<UserProductFundsInfo> saveUserProductFundsInfos(List<UserProductFundsInfo> entitys) {
        logger.info("saveUserProductFundsInfos.entitys = [" + entitys + "]");
        return repository.save(entitys);
    }
}
