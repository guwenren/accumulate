package com.guwr.accumulate.service.authority.core.service;


import java.util.Set;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.core.service.IPermissionService
 * Date 2016/8/13
 * Time 21:07
 */
public interface IPermissionService {

    /**
     * 查看所有权限
     *
     * @param uid
     * @return
     */
    Set<String> findPermissionByUid(int uid);
}
