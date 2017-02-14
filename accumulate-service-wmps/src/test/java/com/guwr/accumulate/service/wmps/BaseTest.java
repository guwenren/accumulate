package com.guwr.accumulate.service.wmps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.service.wmps.BaseTest
 * Date 2016/8/20
 * Time 19:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
public class BaseTest {

    @Test
    public void m1() {
        JedisPool jedisPool;
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxActive(20);
        config.setMaxIdle(5);
//        config.setMaxWait(1000l);
        config.setTestOnBorrow(false);

        jedisPool = new JedisPool(config, "192.168.3.142", 6379);
        Jedis jedis = jedisPool.getResource();
        String set = jedis.set("a", "123");
        System.out.println("set = " + set);
        String aaa = jedis.get("aaa");
        System.out.println("aaa = " + aaa);
        Long a = jedis.del("a");
        System.out.println("a = " + a);
    }
}
