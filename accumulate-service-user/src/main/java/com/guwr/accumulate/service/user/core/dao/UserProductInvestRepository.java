package com.guwr.accumulate.service.user.core.dao;

import com.guwr.accumulate.facade.user.entity.UserProductInvest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.user.core.dao.UserProductInvestRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface UserProductInvestRepository extends JpaRepository<UserProductInvest, Integer> {

    /**
     *
     * @param uid
     * @return
     */
   UserProductInvest findOneByUid(Integer uid);
}
