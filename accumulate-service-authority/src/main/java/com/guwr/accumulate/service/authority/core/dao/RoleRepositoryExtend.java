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
 * Path com.accumulate.service.notify.core.dao.RoleRepositoryExtend
 * Date 2016/8/13
 * Time 21:19
 */
public class RoleRepositoryExtend {

    @PersistenceContext
    private EntityManager entityManager;


    public Set<String> findRoleByUId(int uid) {
        Set<String> stringSet = new HashSet<>();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("");
        sqlBuilder.append(" SELECT o.type FROM tbl_authority_role o");
        sqlBuilder.append(" INNER JOIN tbl_authority_user_role b");
        sqlBuilder.append(" ON o.id = b.rid");
        sqlBuilder.append(" WHERE b.uid = ?");
        Query query = entityManager.createNativeQuery(sqlBuilder.toString());
        query.setParameter(1,uid);
        List content = query.getResultList();
        if (!CollectionUtils.isEmpty(content)) {
            stringSet.addAll(content);
        }
        return stringSet;
    }
}
