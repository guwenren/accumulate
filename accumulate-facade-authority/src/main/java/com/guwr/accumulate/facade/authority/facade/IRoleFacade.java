package com.guwr.accumulate.facade.authority.facade;

import java.util.Set;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.facade.notify.facade.IRoleFacade
 * Date 2016/8/21
 * Time 18:33
 */
public interface IRoleFacade {
    Set<String> findRoleByUid(int uid);
}
