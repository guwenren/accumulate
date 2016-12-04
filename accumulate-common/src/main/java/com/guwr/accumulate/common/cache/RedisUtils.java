package com.guwr.accumulate.common.cache;

import com.guwr.accumulate.common.util.SerializeUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by gwr
 * Description
 * Path com.guwr.accumulate.common.cache.RedisUtils
 * Date 2016/9/6
 * Time 11:17
 */
public class RedisUtils implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(RedisUtils.class);
    /**
     * 默认缓存时间
     */
    private static final int DEFAULT_CACHE_SECONDS = 3600;// 单位秒 设置成一个钟

    /**
     * 连接池
     **/
//    private static JedisSentinelPool jedisPool;
    private static JedisPool jedisPool;

    /**
     * 释放redis资源
     *
     * @param jedis
     */
    private static void releaseResource(Jedis jedis) {
        if (jedis != null) {
            getJedisPool().returnResourceObject(jedis);
        }
    }

    /**
     * 删除Redis中的所有key
     *
     * @throws Exception
     */
    public static void flushAll() {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            jedis.flushAll();
        } catch (Exception e) {
            logger.error("flushAll清空失败：" + e);
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 保存一个对象到Redis中(缓存过期时间:使用此工具类中的默认时间) . <br/>
     *
     * @param key    键 . <br/>
     * @param object 缓存对象 . <br/>
     * @return true or false . <br/>
     * @throws Exception
     */
    public static boolean save(Object key, Object object) {
        return save(key, object, DEFAULT_CACHE_SECONDS);
    }

    /**
     * 保存一个对象到redis中并指定过期时间
     *
     * @param key     键 . <br/>
     * @param object  缓存对象 . <br/>
     * @param seconds 过期时间（单位为秒）.<br/>
     * @return true or false .
     */
    public static boolean save(Object key, Object object, int seconds) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            jedis.set(SerializeUtils.serialize(key), SerializeUtils.serialize(object));
            jedis.expire(SerializeUtils.serialize(key), seconds);
            return true;
        } catch (Exception e) {
            logger.error("Cache保存失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 根据缓存键获取Redis缓存中的值.<br/>
     *
     * @param key 键.<br/>
     * @return Object .<br/>
     * @throws Exception
     */
    public static Object get(Object key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            byte[] obj = jedis.get(SerializeUtils.serialize(key));
            return obj == null ? null : SerializeUtils.unSerialize(obj);
        } catch (Exception e) {
            logger.error("get获取失败：" + e);
            return null;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 根据缓存键清除Redis缓存中的值.<br/>
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static boolean del(Object key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            jedis.del(SerializeUtils.serialize(key));
            return true;
        } catch (Exception e) {
            logger.error("del删除失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 根据缓存键清除Redis缓存中的值.<br/>
     *
     * @param keys
     * @return
     * @throws Exception
     */
    public static boolean del(Object... keys) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            jedis.del(SerializeUtils.serialize(keys));
            return true;
        } catch (Exception e) {
            logger.error("del删除失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * @param key
     * @param seconds 超时时间（单位为秒）
     * @return
     */
    public static boolean expire(Object key, int seconds) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            jedis.expire(SerializeUtils.serialize(key), seconds);
            return true;
        } catch (Exception e) {
            logger.error("expire设置超时时间失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 添加一个内容到指定key的hash中
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static boolean addHash(String key, Object field, Object value) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            jedis.hset(SerializeUtils.serialize(key), SerializeUtils.serialize(field), SerializeUtils.serialize(value));
            return true;
        } catch (Exception e) {
            logger.error("addHash保存失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 从指定hash中拿一个对象
     *
     * @param key
     * @param field
     * @return
     */
    public static Object getHash(Object key, Object field) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            byte[] obj = jedis.hget(SerializeUtils.serialize(key), SerializeUtils.serialize(field));
            return SerializeUtils.unSerialize(obj);
        } catch (Exception e) {
            logger.error("getHash读取失败：" + e);
            return null;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 从hash中删除指定filed的值
     *
     * @param key
     * @param field
     * @return
     */
    public static boolean delHash(Object key, Object field) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            long result = jedis.hdel(SerializeUtils.serialize(key), SerializeUtils.serialize(field));
            return result == 1;
        } catch (Exception e) {
            logger.error("delHash删除失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 拿到缓存中所有符合pattern的key
     *
     * @param pattern
     * @return
     */
    public static Set<byte[]> keys(String pattern) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Set<byte[]> allKey = jedis.keys(("*" + pattern + "*").getBytes());
            return allKey;
        } catch (Exception e) {
            logger.error("keys获取失败：" + e);
            return new HashSet<byte[]>();
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 获得hash中的所有key value
     *
     * @param key
     * @return
     */
    public static Map<byte[], byte[]> getAllHash(Object key) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            Map<byte[], byte[]> map = jedis.hgetAll(SerializeUtils.serialize(key));
            return map;
        } catch (Exception e) {
            logger.error("getAllHash获取失败：" + e);
            return null;
        } finally {
            releaseResource(jedis);
        }
    }

    /**
     * 判断一个key是否存在
     *
     * @param key
     * @return
     */
    public static Boolean exists(Object key) {
        Jedis jedis = null;
        Boolean result = false;
        try {
            jedis = getJedisPool().getResource();
            result = jedis.exists(SerializeUtils.serialize(key));
            return result;
        } catch (Exception e) {
            logger.error("exists获取失败：" + e);
            return false;
        } finally {
            releaseResource(jedis);
        }
    }

    public void setJedisPool(JedisPool jedisPool) {
        RedisUtils.jedisPool = jedisPool;
    }

    public static JedisPool getJedisPool() {
        return jedisPool;
    }

    public static void main(String[] args) {

    }

    private static void logger() {
        logger.trace("======trace");
        logger.debug("======debug");
        logger.info("======info");
        logger.warn("======warn");
        logger.error("======error");
    }

    private static void deserialize() {
        String abc = "abc";
        byte[] objectData = SerializationUtils.serialize(abc);
        System.out.println(objectData);
        Object deserialize = SerializationUtils.deserialize(objectData);
        System.out.println(deserialize);
    }
}
