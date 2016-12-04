package com.guwr.accumulate.service.wmps.core.service;


import com.guwr.accumulate.facade.wmps.entity.Product;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.service.IProductService
 * Date 2016/8/13
 * Time 21:07
 */
public interface IProductService {
    Product save(Product entity);

    Product update(Product entity);

    Product findOne(Integer id);
}
