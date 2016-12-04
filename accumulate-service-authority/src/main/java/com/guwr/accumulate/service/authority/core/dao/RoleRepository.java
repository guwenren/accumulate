package com.guwr.accumulate.service.authority.core.dao;

import com.guwr.accumulate.facade.authority.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.notify.core.dao.RoleRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Set<String> findRoleByUId(int uid);
}
