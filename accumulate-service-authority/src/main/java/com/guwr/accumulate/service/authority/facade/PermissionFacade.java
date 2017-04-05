package com.guwr.accumulate.service.authority.facade;

import com.guwr.accumulate.facade.authority.entity.Permission;
import com.guwr.accumulate.facade.authority.facade.IPermissionFacade;
import com.guwr.accumulate.service.authority.core.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by guwr
 * Project_name accumulate
 * Path com.guwr.accumulate.service.authority.facade.PermissionFacade
 * Date 2017/4/5
 * Time 14:26
 * Description
 */
@Component
public class PermissionFacade implements IPermissionFacade {


    @Autowired
    private IPermissionService permissionService;

    @Override
    public Set<String> findPermissionByUid(int uid) {
        return permissionService.findPermissionByUid(uid);
    }
}
