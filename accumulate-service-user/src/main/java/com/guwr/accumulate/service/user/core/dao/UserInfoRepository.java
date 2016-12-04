package com.guwr.accumulate.service.user.core.dao;

import com.guwr.accumulate.common.page.PageParam;
import com.guwr.accumulate.facade.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.accumulate.service.user.core.dao.UserInfoRepository
 * Date 2016/8/13
 * Time 21:19
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    List<UserInfo> findOneByMobile(String mobile);

    Integer listPageCount(String listPageCount);

    List<UserInfo> listPage(String listPage, PageParam pageParam);
}
