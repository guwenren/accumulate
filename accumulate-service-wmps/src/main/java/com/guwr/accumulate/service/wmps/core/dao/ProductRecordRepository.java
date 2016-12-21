package com.guwr.accumulate.service.wmps.core.dao;

import com.guwr.accumulate.facade.wmps.entity.ProductRecord;
import com.guwr.accumulate.facade.wmps.entity.ProductRecordExtend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.wmps.core.dao.ProductRecordRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface ProductRecordRepository extends JpaRepository<ProductRecord, Integer> {

    /**
     *
     * @param uuid
     * @return
     */
    ProductRecord findOneProductRecordByUUID(String uuid);

    /**
     *
     * @param interestDate
     * @return
     */
    int findInterestCount(int interestDate);

    /**
     * 查找需要发息的用户ID
     * @param mod
     * @param number
     * @param interestDate
     * @return
     */
    List<Integer> findListByMOD(Integer mod,Integer number, Integer interestDate);


    /**
     * 查找需要发息的用户信息
     * @param mod
     * @param number
     * @param interestDate
     * @return
     */
    List<ProductRecordExtend> findProductRecordExtendListByMOD(Integer mod, Integer number, Integer interestDate);

}
