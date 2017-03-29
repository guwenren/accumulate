package com.guwr.accumulate.service.test.cache;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Created by guwr
 * Project_name accumulate
 * Path com.guwr.accumulate.service.test.cache.CacheMain
 * Date 2017/3/29
 * Time 14:44
 * Description
 */
public class CacheMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
        context.start();
        System.out.println("context = " + context);

        ShardedJedisPool pool = (ShardedJedisPool) context.getBean("shardedJedisPool");
        ShardedJedis jedis = pool.getResource();
        System.out.println("jedis = " + jedis);

    }

    private static void set(ShardedJedis jedis) {
        jedis.set("zhang", "zsf");
    }

    private static void get(ShardedJedis jedis) {
        String v = jedis.get("zhang");
        System.out.println("v = " + v);
    }
}
