package com.guwr.accumulate.service.authority.core.service.impl;

import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.service.authority.BaseTest;
import com.guwr.accumulate.service.authority.core.service.IRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.core.service.impl.RoleServiceTest
 * Date 2016/9/5
 * Time 13:58
 */
public class RoleServiceTest extends BaseTest {

    @Autowired
    private IRoleService roleService;

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void findRoleByUId() throws Exception {
        Set<String> stringSet = roleService.findRoleByUId(1);
        System.out.println(stringSet.size());
        CommonUtils.printObj2Json(stringSet);
    }
}