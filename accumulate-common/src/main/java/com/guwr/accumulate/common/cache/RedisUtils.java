package com.guwr.accumulate.common.cache;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.common.cache.RedisUtils
 * Date 2016/9/6
 * Time 11:17
 */
public class RedisUtils implements Serializable {

    /**
     * 默认缓存时间
     */
    private static final int DEFAULT_CACHE_SECONDS = 60 * 60 * 1;// 单位秒 设置成一个钟
    private static Logger logger = Logger.getLogger(RedisUtils.class);
    /**
     * 连接池
     **/
    @Autowired
    private JedisSentinelPool jedisSentinelPool;

    public static void main(String[] args) {
        Integer a = 444;
        Integer b = 444;
        System.out.println("a = b，" + (a.intValue() == b.intValue()));
        System.out.println(Objects.equals(a, b));
        String str = "a,b,c,,"; 
        String[] ary = str.split(",");
        System.out.println("ary.length = " + ary.length);


        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");
        List<String> strings = stringList.subList(0, 2);

        System.out.println("strings = " + strings);

        String[] strings1 = strings.toArray(new String[strings.size()]);

        System.out.println("strings1 = " + strings1);
    }

    public JedisSentinelPool getJedisSentinelPool() {
        return jedisSentinelPool;
    }

//    JedisPool jedisPool;
//    // 池基本配置
//    JedisPoolConfig config = new JedisPoolConfig();
////        config.setMaxActive(20);
//        config.setMaxIdle(5);
////        config.setMaxWait(1000l);
//        config.setTestOnBorrow(false);
//
//    jedisPool = new JedisPool(config, "192.168.3.142", 6379);
//    Jedis jedis = jedisPool.getResource();
//    String set = jedis.set("a", "123");
//        System.out.println("set = " + set);
//    String aaa = jedis.get("aaa");
//        System.out.println("aaa = " + aaa);
//    Long a = jedis.del("a");
//        System.out.println("a = " + a);

    public void setJedisSentinelPool(JedisSentinelPool jedisSentinelPool) {
        Jedis resource = jedisSentinelPool.getResource();
        this.jedisSentinelPool = jedisSentinelPool;
    }
}
