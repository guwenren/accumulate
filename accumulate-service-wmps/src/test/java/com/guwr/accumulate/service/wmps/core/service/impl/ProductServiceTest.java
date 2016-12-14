package com.guwr.accumulate.service.wmps.core.service.impl;

import com.guwr.accumulate.common.util.DateUtils;
import com.guwr.accumulate.facade.wmps.entity.Product;
import com.guwr.accumulate.facade.wmps.enums.ProductStatus;
import com.guwr.accumulate.service.wmps.BaseTest;
import com.guwr.accumulate.service.wmps.core.service.IProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.core.service.impl.ProductServiceTest
 * Date 2016/9/8
 * Time 15:46
 */
public class ProductServiceTest extends BaseTest {


    @Autowired
    private IProductService productService;

    @Test
    public void save() throws Exception {
        for (int i = 0; i < 10; i++) {
            Date date = new Date();

            int day = 3;
            Date enddate = DateUtils.addDay(date, 3);
            Date interdate = DateUtils.addDay(date, 1);

            String uuid = com.guwr.accumulate.common.util.StringUtils.getUUID();
            Product product = new Product();
            product.setAmount(new BigDecimal(1000000));
            product.setEffectAmount(BigDecimal.ZERO);
            product.setInvestAmount(BigDecimal.ZERO);
            product.setInterestrate(new BigDecimal(0.073));
            product.setPhases(day);
            product.setCreateTime(date);
            product.setUpdateTime(date);
            product.setPublisheddate(date);
            product.setInterdate(interdate);
            product.setEnddate(enddate);
            product.setUuid(uuid);
            product.setStatus(ProductStatus.PUBLISHED.getValue());
            productService.save(product);
        }
    }

    @Test
    public void findOne() throws Exception {
        Product product = productService.findOne(1);
        product.setAmount(new BigDecimal(100000));
        productService.save(product);
        System.out.println("product = " + product);
    }
}