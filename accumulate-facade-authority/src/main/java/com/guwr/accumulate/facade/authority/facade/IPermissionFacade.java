package com.guwr.accumulate.facade.authority.facade;

import com.guwr.accumulate.facade.authority.entity.Permission;

import java.util.Set;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.notify.facade.IPermissionFacade
 * Date 2016/8/21
 * Time 18:33
 */
public interface IPermissionFacade {
    Set<String> findPermissionByUid(int uid);
}
