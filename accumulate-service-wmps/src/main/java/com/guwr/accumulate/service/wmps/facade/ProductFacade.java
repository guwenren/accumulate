package com.guwr.accumulate.service.wmps.core.facade;

import com.guwr.accumulate.facade.wmps.entity.Product;
import com.guwr.accumulate.facade.wmps.facade.IProductFacade;
import com.guwr.accumulate.service.wmps.core.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.facade.ProductFacade
 * Date 2016/8/21
 * Time 18:31
 */
@Component
public class ProductFacade implements IProductFacade {
    @Autowired
    private IProductService service;

    @Override
    public Product save(Product entity) {
        return service.save(entity);
    }
}
