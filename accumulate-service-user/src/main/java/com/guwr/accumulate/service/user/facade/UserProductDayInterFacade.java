package com.guwr.accumulate.service.user.facade;

import com.guwr.accumulate.facade.user.entity.UserProductDayInter;
import com.guwr.accumulate.facade.user.facade.IUserProductDayInterFacade;
import com.guwr.accumulate.service.user.core.service.IUserProductDayInterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.user.facade.UserProductDayInterFacade
 * Date 2016/8/21
 * Time 18:31
 */
@Component
public class UserProductDayInterFacade implements IUserProductDayInterFacade {

    private static Logger logger = LoggerFactory.getLogger(UserProductDayInterFacade.class);

    @Autowired
    private IUserProductDayInterService userProductDayInterService;

    @Override
    public List<UserProductDayInter> saveUserProductDayInters(List<UserProductDayInter> entitys) {
        logger.info("saveUserProductDayInters.entitys = [" + entitys + "]");
        return userProductDayInterService.saveUserProductDayInters(entitys);
    }
}
