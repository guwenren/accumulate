package com.guwr.accumulate.service.user.core.dao;

import com.guwr.accumulate.facade.user.entity.UserProductEarnings;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.wmps.core.dao.UserProductEarningsRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface UserProductEarningsRepository extends JpaRepository<UserProductEarnings, Integer> {

    UserProductEarnings findOneByUidPidLid(Integer uid, Integer pid, Integer lid);
}
