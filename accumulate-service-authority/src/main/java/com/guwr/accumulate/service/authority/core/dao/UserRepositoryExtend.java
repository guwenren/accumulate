package com.guwr.accumulate.service.authority.core.dao;

import com.guwr.accumulate.facade.authority.entity.User;
import com.guwr.accumulate.facade.authority.vo.UserVO;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.authority.core.dao.UserRepositoryExtend
 * Date 2016/8/13
 * Time 21:19
 */
public class UserRepositoryExtend {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findUserByEmail(String username) {
        String qlString = " from User o where o.username = ?";
        Query query = entityManager.createQuery(qlString);
        query.setParameter(1, username);
        return query.getResultList();
    }

    public Page<User> pageInfo(UserVO vo) {
        String sqlString = " select * from tbl_authority_user o";
        String sqlStringCount = " select count(id) from tbl_authority_user o";
        Integer page = vo.getPageNum();
        Integer size = vo.getNumPerPage();
        Pageable pageable = new PageRequest(page, size);
        Query query = entityManager.createNativeQuery(sqlStringCount);
        BigInteger singleResult = (BigInteger) query.getSingleResult();
        List<User> content = new ArrayList();
        final long total = singleResult.longValue();
        if (total > 0) {
            int firstResult = (page - 1) * size;
            query = entityManager.createNativeQuery(sqlString);
            query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(User.class));
            query.setFirstResult(firstResult);
            query.setMaxResults(size);
            content = query.getResultList();
        }
        PageImpl userPage = new PageImpl(content, pageable, total);
        return userPage;
    }
}

