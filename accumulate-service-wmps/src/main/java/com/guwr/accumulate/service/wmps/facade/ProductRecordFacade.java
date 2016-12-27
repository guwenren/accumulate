package com.guwr.accumulate.service.wmps.facade;

import com.guwr.accumulate.facade.wmps.entity.ProductRecord;
import com.guwr.accumulate.facade.wmps.facade.IProductRecordFacade;
import com.guwr.accumulate.facade.wmps.vo.ProductRecordVO;
import com.guwr.accumulate.service.wmps.core.service.IProductRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.facade.ProductRecordFacade
 * Date 2016/8/21
 * Time 18:31
 */
@Component
public class ProductRecordFacade implements IProductRecordFacade {

    private static Logger logger = LoggerFactory.getLogger(ProductRecordFacade.class);

    @Autowired
    private IProductRecordService service;

    @Override
    public ProductRecord save(ProductRecord entity) {
        Integer uid = entity.getUid();
        logger.info("{}_save.entity = [" + entity + "]", uid);
        return service.save(entity);
    }

    @Override
    public ProductRecord findOneProductRecordByUUID(String uuid) {
        logger.info("findOneProductRecordByUUID.uuid = [" + uuid + "]");
        return service.findOneProductRecordByUUID(uuid);
    }

    @Override
    public void updateProearnAndInterestrateByUUID(ProductRecordVO info) {
        logger.info("updateProearnAndInterestrateByUUID.info = [" + info + "]");
        service.updateProearnAndInterestrateByUUID(info);
    }
}
