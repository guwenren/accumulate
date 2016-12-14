package com.guwr.accumulate.service.wmps.core.dao;

import com.guwr.accumulate.facade.wmps.entity.ProductRecord;
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
     * @param number
     * @param interestDate
     * @return
     */
    List<Integer> findListByMOD(Integer number, Integer interestDate);
}
