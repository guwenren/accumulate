package com.guwr.accumulate.service.authority.core.dao;

import com.guwr.accumulate.facade.authority.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.notify.core.dao.PermissionRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface PermissionRepository extends JpaRepository<RolePermission, Integer> {

    Set<String> findPermissionByUid(int uid);
}
