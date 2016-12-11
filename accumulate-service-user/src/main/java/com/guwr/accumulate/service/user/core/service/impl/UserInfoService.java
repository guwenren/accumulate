package com.guwr.accumulate.service.user.core.service.impl;


import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.common.page.PageParam;
import com.guwr.accumulate.facade.account.facade.IAccountBalanceFacade;
import com.guwr.accumulate.facade.account.vo.AccountBalanceVO;
import com.guwr.accumulate.facade.notify.facade.INotifyMessageFacade;
import com.guwr.accumulate.facade.notify.vo.NotifyMessageVO;
import com.guwr.accumulate.facade.user.entity.UserInfo;
import com.guwr.accumulate.facade.user.exception.UserBizException;
import com.guwr.accumulate.facade.user.vo.UserInfoVO;
import com.guwr.accumulate.service.user.core.dao.UserInfoRepository;
import com.guwr.accumulate.service.user.core.service.IUserInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.user.service.impl.UserInfoService
 * Date 2016/8/13
 * Time 21:16
 */
@Service
public class UserInfoService implements IUserInfoService {

    private static Logger logger = LoggerFactory.getLogger(UserInfoService.class);
    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private IAccountBalanceFacade accountBalanceFacade;

    @Autowired
    private INotifyMessageFacade notifyMessageFacade;

    @Override
    public UserInfo save(UserInfo entity) {
        return repository.save(entity);
    }

    @Override
    public UserInfo edit(UserInfo entity) {
        return repository.save(entity);
    }

    @Override
    public UserInfo findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    @Transactional
    public UserInfo register(UserInfoVO info) {
        String uuid = com.guwr.accumulate.common.util.StringUtils.getUUID();
        // 1、用户注册
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(info.getPassword());
        userInfo.setMobile(info.getMobile());
        userInfo.setRealname(info.getRealname());
        userInfo = save(userInfo);

        Integer id = userInfo.getId();
        // 2、添加用户账户
        AccountBalanceVO accountBalanceVO = new AccountBalanceVO();
        accountBalanceVO.setUid(id);
        accountBalanceFacade.save(accountBalanceVO);

        // 3、发送短信通知
        NotifyMessageVO notifyMessageVO = new NotifyMessageVO();
        notifyMessageVO.setUid(id);
        notifyMessageVO.setUuid(uuid);
        notifyMessageFacade.save(notifyMessageVO);
        return userInfo;
    }

    @Override
    public UserInfo findOneByMobile(String mobile) {
        logger.info("UserInfoService.findOneByMobile");
        if (StringUtils.isBlank(mobile)) {
            throw UserBizException.SHOU_JI_HAO_MA_BU_NENG_WEI_KONG.print();
        }
        List<UserInfo> userInfos = repository.findOneByMobile(mobile);
        if (CollectionUtils.isEmpty(userInfos)) {
            throw UserBizException.YONG_HU_BU_CUN_ZAI.print();
        }
        return userInfos.get(0);
    }

    @Override
    public PageBean<UserInfo> findListPage(UserInfoVO info) {
        PageBean<UserInfo> pageBean;
        Integer pageNum = info.getPageNum();
        Integer numPerPage = info.getNumPerPage();
        String mobile = info.getMobile();
        String realname = info.getRealname();
        PageParam pageParam = new PageParam(pageNum, numPerPage);
        StringBuilder conditionSql = new StringBuilder();
        String listPage = " select id,password,realname,mobile from ";
        String listPageCount = " select count(1) from ";
        conditionSql.append(" tbl_user_userinfo a");
        conditionSql.append(" where 1 = 1");

        String orderBy = " order by a.id desc";
        if (StringUtils.isNotBlank(mobile)) {
            conditionSql.append(" and a.mobile like '%").append(mobile).append("%'");
        }
        if (StringUtils.isNotBlank(realname)) {
            conditionSql.append(" and a.realname like '%").append(realname).append("%'");
        }
        String listPageCountStr = listPageCount + conditionSql.toString();
        logger.info(listPageCountStr);
        int total = repository.listPageCount(listPageCountStr);
        if (total <= 0) {
            return new PageBean<>(pageNum, numPerPage, total, null);
        }
        String listPageStr = listPage + conditionSql.toString() + orderBy;
        logger.info(listPageStr);
        List<UserInfo> recordList = repository.listPage(listPageStr, pageParam);
        pageBean = new PageBean<>(pageNum, numPerPage, total, recordList);
        return pageBean;
    }
}
