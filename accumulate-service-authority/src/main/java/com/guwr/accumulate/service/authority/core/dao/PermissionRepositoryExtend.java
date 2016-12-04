package com.guwr.accumulate.service.authority.core.dao;

import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.notify.core.dao.PermissionRepositoryExtend
 * Date 2016/8/13
 * Time 21:19
 */
public class PermissionRepositoryExtend {

    @PersistenceContext
    private EntityManager entityManager;


    public Set<String> findPermissionByUid(int uid) {
        Set<String> stringSet = new HashSet<>();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" SELECT o.url FROM tbl_authority_permission o");
        sqlBuilder.append(" INNER JOIN tbl_authority_role_permission b");
        sqlBuilder.append(" ON o.id = b.pid");
        sqlBuilder.append(" INNER JOIN  tbl_authority_user_role c");
        sqlBuilder.append(" ON b.rid = c.rid");
        sqlBuilder.append(" WHERE c.uid = ?");
        Query query = entityManager.createNativeQuery(sqlBuilder.toString());
        query.setParameter(1, uid);
        List content = query.getResultList();
        if (!CollectionUtils.isEmpty(content)) {
            stringSet.addAll(content);
        }
        return stringSet;
    }
}
