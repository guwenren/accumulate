package com.guwr.accumulate.service.authority.facade;

import com.guwr.accumulate.facade.authority.entity.Permission;
import com.guwr.accumulate.facade.authority.facade.IPermissionFacade;
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


    @Override
    public Set<String> findPermissionByUid(int uid) {
        return null;
    }
}
