package com.guwr.accumulate.service.wmps.core.service;


import com.guwr.accumulate.facade.wmps.entity.ProductRecord;
import com.guwr.accumulate.facade.wmps.entity.ProductRecordExtend;
import com.guwr.accumulate.facade.wmps.vo.ProductRecordVO;

import java.util.List;

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
     * 发息定时任务
     */
    void interestTask();

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

    /**
     * 更新投资记录
     *
     * @param productRecord
     * @return
     */
    ProductRecord updateProductRecord(ProductRecord productRecord);

    /**
     * 需要发息的人数
     *
     * @return
     */
    int findInterestCount(int interestDate);

    /**
     * 根据MOD查找发息用户
     *
     * @param mod
     * @param number
     * @param interestDate
     * @return
     */
    List<Integer> findListByMOD(Integer mod, Integer number, Integer interestDate);

    /**
     * 根据MOD查找发息model
     *
     * @param mod
     * @param number
     * @param interestDate
     * @return
     */
    List<ProductRecordExtend> findProductRecordExtendListByMOD(Integer mod, Integer number, Integer interestDate);

    /**
     * 更新投资记录逾期收益与年利率
     *
     * @param info
     * @return
     */
    void updateProearnAndInterestrateByUUID(ProductRecordVO info);
}
