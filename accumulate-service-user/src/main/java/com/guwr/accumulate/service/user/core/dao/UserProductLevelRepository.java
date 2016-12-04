package com.guwr.accumulate.service.user.core.dao;

import com.guwr.accumulate.facade.user.entity.UserProductLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.user.core.dao.UserProductLevelRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface UserProductLevelRepository extends JpaRepository<UserProductLevel, Integer> {

    UserProductLevel findOneByInvest(BigDecimal invest);
}
