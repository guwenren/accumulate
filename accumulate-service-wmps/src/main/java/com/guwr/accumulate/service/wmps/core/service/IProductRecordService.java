package com.guwr.accumulate.service.wmps.core.service;


import com.guwr.accumulate.facade.wmps.entity.ProductRecord;
import com.guwr.accumulate.facade.wmps.vo.ProductRecordVO;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.service.IProductRecordService
 * Date 2016/8/13
 * Time 21:07
 */
public interface IProductRecordService {
    /**
     * 保存购买记录
     *
     * @param entity
     * @return
     */
    ProductRecord save(ProductRecord entity);

    /**
     * 查找购买记录
     *
     * @param id
     * @return
     */
    ProductRecord findOne(Integer id);

    /**
     * 添加购买记录
     *
     * @param info
     */
    void addProductRecord(ProductRecordVO info);


    /**
     * 根据投资UUID获取对象
     *
     * @param uuid
     * @return
     */
    ProductRecord findOneProductRecordByUUID(String uuid);
}
