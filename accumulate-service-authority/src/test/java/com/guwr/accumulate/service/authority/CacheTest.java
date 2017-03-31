package com.guwr.accumulate.service.authority;

import com.guwr.accumulate.common.cache.RedisUtils;
import com.guwr.accumulate.facade.authority.entity.User;
import org.junit.Test;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.authority.CacheTest
 * Date 2016/9/6
 * Time 11:44
 */
public class CacheTest extends BaseTest {

    @Test
    public void testSave() {
        User user = new User();
        user.setId(1);
        boolean user1 = false;//RedisUtils.save("user", user);
        System.out.println(user1);


    }

    @Test
    public void testGet() {
        Object o = null;//RedisUtils.get("user");
        System.out.println("o = " + o);
    }
}
