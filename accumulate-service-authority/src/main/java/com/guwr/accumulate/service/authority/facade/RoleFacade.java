package com.guwr.accumulate.service.authority.facade;

import com.guwr.accumulate.facade.authority.facade.IRoleFacade;
import com.guwr.accumulate.service.authority.core.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by guwr
 * Project_name accumulate
 * Path com.guwr.accumulate.service.authority.facade.RoleFacade
 * Date 2017/4/5
 * Time 14:26
 * Description
 */
@Component
public class RoleFacade implements IRoleFacade {


    @Autowired
    private IRoleService roleService;

    @Override
    public Set<String> findRoleByUid(int uid) {
        return roleService.findRoleByUId(uid);
    }
}
