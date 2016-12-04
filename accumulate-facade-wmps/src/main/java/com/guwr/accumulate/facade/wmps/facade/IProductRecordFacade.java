package com.guwr.accumulate.facade.wmps.facade;


import com.guwr.accumulate.facade.wmps.entity.ProductRecord;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.wmps.facade.IProductFacade
 * Date 2016/8/21
 * Time 18:33
 */
public interface IProductRecordFacade {
    ProductRecord save(ProductRecord entity);

    ProductRecord findOneProductRecordByUUID(String uuid);
}
