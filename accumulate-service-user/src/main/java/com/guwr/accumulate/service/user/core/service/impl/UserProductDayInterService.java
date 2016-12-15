package com.guwr.accumulate.service.user.core.service.impl;


import com.guwr.accumulate.facade.user.entity.UserProductDayInter;
import com.guwr.accumulate.service.user.core.dao.UserProductDayInterRepository;
import com.guwr.accumulate.service.user.core.service.IUserProductDayInterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.user.service.impl.UserProductDayInterService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class UserProductDayInterService implements IUserProductDayInterService {

    private static Logger logger = LoggerFactory.getLogger(UserProductDayInterService.class);

    @Autowired
    private UserProductDayInterRepository repository;

    @Override
    public UserProductDayInter saveUserProductDayInter(UserProductDayInter entity) {
        logger.info("saveUserProductDayInter.entity = [" + entity + "]");
        return repository.save(entity);
    }

    @Override
    public List<UserProductDayInter> saveUserProductDayInters(List<UserProductDayInter> entitys) {
        logger.info("saveUserProductDayInters.entitys = [" + entitys + "]");
        return repository.save(entitys);
    }

    @Override
    public UserProductDayInter editUserProductDayInter(UserProductDayInter entity) {
        logger.info("editUserProductDayInter.entity = [" + entity + "]");
        return repository.save(entity);
    }
}
