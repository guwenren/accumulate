package com.guwr.accumulate.service.authority.core.service.impl;

import com.guwr.accumulate.common.util.CommonUtils;
import com.guwr.accumulate.service.authority.BaseTest;
import com.guwr.accumulate.service.authority.core.service.IPermissionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.core.service.impl.PermissionServiceTest
 * Date 2016/9/5
 * Time 13:39
 */
public class PermissionServiceTest extends BaseTest {

    @Autowired
    private IPermissionService permissionService;

    @Test
    public void findPermissionByUid() throws Exception {
        Set<String> stringSet = permissionService.findPermissionByUid(1);
        System.out.println(stringSet.size());
        CommonUtils.printObj2Json(stringSet);
    }
}