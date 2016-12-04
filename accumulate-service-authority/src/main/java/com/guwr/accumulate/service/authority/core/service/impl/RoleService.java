package com.guwr.accumulate.service.authority.core.service.impl;


import com.guwr.accumulate.facade.authority.entity.Role;
import com.guwr.accumulate.service.authority.core.dao.RoleRepository;
import com.guwr.accumulate.service.authority.core.service.IPermissionService;
import com.guwr.accumulate.service.authority.core.service.IRoleService;
import com.guwr.accumulate.service.authority.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.core.service.impl.RoleService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository repository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPermissionService rolePermissionService;

    @Override
    @Transactional
    public void delete(int id){
        repository.delete(id);
    }

    @Override
    @Transactional
    public Role save(Role entity){
        return repository.save(entity);
    }

    @Override
    public Set<String> findRoleByUId(int uid){
        return repository.findRoleByUId(uid);
    }
}
