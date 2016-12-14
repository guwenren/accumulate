package com.guwr.accumulate.service.user.core.service.impl;

import com.guwr.accumulate.common.page.PageBean;
import com.guwr.accumulate.common.util.StringUtils;
import com.guwr.accumulate.common.util.UUIDUitls;
import com.guwr.accumulate.facade.user.entity.UserInfo;
import com.guwr.accumulate.facade.user.vo.UserInfoVO;
import com.guwr.accumulate.service.user.BaseTest;
import com.guwr.accumulate.service.user.core.service.IUserInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.user.core.service.impl.UserInfoServiceTest
 * Date 2016/9/12
 * Time 10:49
 */
public class UserInfoServiceTest extends BaseTest {
    @Autowired
    private IUserInfoService userInfoService;

    @Test
    public void findOneByMobile() throws Exception {
        UserInfo userInfo = userInfoService.findOneByMobile("13167309518");
        System.out.println("userInfo = " + userInfo);
    }

    @Test
    public void findListPage() throws Exception {
        UserInfoVO info = new UserInfoVO();
        info.setPageNum(2);
        info.setNumPerPage(10);
        PageBean<UserInfo> listPage = userInfoService.findListPage(info);
        System.out.println("listPage = " + listPage);
    }

    @Test
    public void save() throws Exception {
//        for (int i = 0; i < 20; i++) {
        UserInfo userInfo = new UserInfo();
        userInfo.setMobile("13" + UUIDUitls.generateInteger(9));
        userInfo.setPassword(UUIDUitls.generateString(6));
        userInfo.setRealname("李" + UUIDUitls.generateString(8));
        userInfoService.save(userInfo);
        System.out.println(userInfoService);
//        }
    }

    @Test
    public void findOne() throws Exception {
        UserInfo userInfo = userInfoService.findOne(1);
        System.out.println("userInfo = " + userInfo);
    }

    @Test
    public void register() throws Exception {
        for (int i = 1; i < 15; i++) {
            UserInfoVO info = new UserInfoVO();
            info.setMobile("13" + UUIDUitls.generateInteger(9));
            info.setPassword(UUIDUitls.generateString(6));
            info.setRealname("李" + UUIDUitls.generateString(8));
            info.setUuid(StringUtils.getUUID());
            userInfoService.register(info);
        }
    }
}