package com.guwr.accumulate.service.authority.core.service;


import com.guwr.accumulate.facade.authority.entity.Role;
import com.guwr.accumulate.facade.authority.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.core.service.IRoleService
 * Date 2016/8/13
 * Time 21:07
 */
public interface IRoleService {

    void delete(int id);
    Role save(Role entity);

    Set<String> findRoleByUId(int uid);
}
