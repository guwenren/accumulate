package com.guwr.accumulate.service.user.core.service.impl;


import com.guwr.accumulate.facade.user.entity.UserProductEarnings;
import com.guwr.accumulate.service.user.core.dao.UserProductEarningsRepository;
import com.guwr.accumulate.service.user.core.service.IUserProductEarningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.service.impl.UserProductEarningsService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class UserProductEarningsService implements IUserProductEarningsService {

    @Autowired
    private UserProductEarningsRepository repository;


    @Override
    public UserProductEarnings save(UserProductEarnings entity) {
        return repository.save(entity);
    }

    @Override
    public UserProductEarnings update(UserProductEarnings entity) {
        return repository.save(entity);
    }

    @Override
    public UserProductEarnings findOneByUidPidLid(Integer uid, Integer pid, Integer lid) {
        return repository.findOneByUidPidLid(uid, pid, lid);
    }
}
