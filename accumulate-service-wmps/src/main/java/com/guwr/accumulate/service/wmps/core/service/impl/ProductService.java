package com.guwr.accumulate.service.wmps.core.service.impl;


import com.guwr.accumulate.facade.wmps.entity.Product;
import com.guwr.accumulate.facade.wmps.exception.WmpsException;
import com.guwr.accumulate.service.wmps.core.dao.ProductRepository;
import com.guwr.accumulate.service.wmps.core.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.service.impl.ProductService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repository;


    @Override
    public Product save(Product entity) {
        return repository.save(entity);
    }

    @Override
    public Product update(Product entity){
        return repository.save(entity);
    }

    @Override
    public Product findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Product findOneCheck(Integer id) {
        Product product = this.findOne(id);
        if (product == null) { //产品是否存在
            throw WmpsException.CHAN_PIN_BU_CUN_ZAI.print();
        }
        return product;
    }
}
