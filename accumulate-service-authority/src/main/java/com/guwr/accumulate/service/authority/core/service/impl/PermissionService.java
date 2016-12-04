package com.guwr.accumulate.service.authority.core.service.impl;


import com.guwr.accumulate.service.authority.core.dao.PermissionRepository;
import com.guwr.accumulate.service.authority.core.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.core.service.impl.PermissionService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class PermissionService implements IPermissionService {


    @Autowired
    private PermissionRepository repository;

    @Override
    public Set<String> findPermissionByUid(int uid) {
        return  repository.findPermissionByUid(uid);
    }
}
