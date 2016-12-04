package com.guwr.accumulate.service.wmps.core.dao;

import com.guwr.accumulate.facade.wmps.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.wmps.core.dao.ProductRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
