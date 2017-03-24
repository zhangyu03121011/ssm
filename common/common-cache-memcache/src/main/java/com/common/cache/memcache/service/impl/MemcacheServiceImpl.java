package com.common.cache.memcache.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.base.common.BaseLogger;
import com.common.cache.memcache.service.IMemcacheService;
import com.common.util.ExceptionUtil;
import com.danga.MemCached.MemCachedClient;

/**
 * Memcache Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月20日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
@Service
public class MemcacheServiceImpl extends BaseLogger implements IMemcacheService {

    @Autowired
    private MemCachedClient memcachedClient;

    /**
     * 向缓存添加新的键值对。如果键已经存在，则之前的值将被替换。
     * 
     * @param key
     *            键
     * @param value
     *            值
     * @return
     */
    public boolean set(String key, Object value) throws Exception {
        return setExp(key, value, 0);
    }

    /**
     * 向缓存添加新的键值对。如果键已经存在，则之前的值将被替换。
     * 
     * @param key
     *            键
     * @param value
     *            值
     * @param expire
     *            过期时间 New Date(1000*10)：十秒后过期
     * @return
     */
    public boolean set(String key, Object value, long expire) throws Exception {
        return setExp(key, value, expire);
    }

    /**
     * 向缓存添加新的键值对。如果键已经存在，则之前的值将被替换。
     * 
     * @param key
     *            键
     * @param value
     *            值
     * @param expire
     *            过期时间 New Date(1000*10)：十秒后过期
     * @return
     */
    private boolean setExp(String key, Object value, long expire) throws Exception {
        boolean flag = false;
        try {
            flag = memcachedClient.set(key, value, new Date(expire * 1000));
            logger.info("[MemcacheServiceImpl][set][key=" + key + "][flag=" + flag + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return flag;
    }

    /**
     * 仅当缓存中不存在键时，add 命令才会向缓存中添加一个键值对。
     * 
     * @param key
     *            键
     * @param value
     *            值
     * @return
     */
    public boolean add(String key, Object value) throws Exception {
        return addExp(key, value, 0);
    }

    /**
     * 仅当缓存中不存在键时，add 命令才会向缓存中添加一个键值对。
     * 
     * @param key
     *            键
     * @param value
     *            值
     * @param expire
     *            过期时间 New Date(1000*10)：十秒后过期
     * @return
     */
    public boolean add(String key, Object value, long expire) throws Exception {
        return addExp(key, value, expire);
    }

    /**
     * 仅当缓存中不存在键时，add 命令才会向缓存中添加一个键值对。
     * 
     * @param key
     *            键
     * @param value
     *            值
     * @param expire
     *            过期时间 New Date(1000*10)：十秒后过期
     * @return
     */
    private boolean addExp(String key, Object value, long expire) throws Exception {
        boolean flag = false;
        try {
            flag = memcachedClient.add(key, value, new Date(expire * 1000));
            logger.info("[MemcacheServiceImpl][add][key=" + key + "][flag=" + flag + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return flag;
    }

    /**
     * 仅当键已经存在时，replace 命令才会替换缓存中的键。
     * 
     * @param key
     *            键
     * @param value
     *            值
     * @return
     */
    public boolean replace(String key, Object value) throws Exception {
        return replaceExp(key, value, 0);
    }

    /**
     * 仅当键已经存在时，replace 命令才会替换缓存中的键。
     * 
     * @param key
     *            键
     * @param value
     *            值
     * @param expire
     *            过期时间 New Date(1000*10)：十秒后过期
     * @return
     */
    public boolean replace(String key, Object value, long expire) throws Exception {
        return replaceExp(key, value, expire);
    }

    /**
     * 仅当键已经存在时，replace 命令才会替换缓存中的键。
     * 
     * @param key
     *            键
     * @param value
     *            值
     * @param expire
     *            过期时间 New Date(1000*10)：十秒后过期
     * @return
     */
    private boolean replaceExp(String key, Object value, long expire) throws Exception {
        boolean flag = false;
        try {
            flag = memcachedClient.replace(key, value, new Date(expire * 1000));
            logger.info("[MemcacheServiceImpl][replace][key=" + key + "][flag=" + flag + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return flag;
    }

    /**
     * get 命令用于检索与之前添加的键值对相关的值。
     * 
     * @param key
     *            键
     * @return
     */
    public Object get(String key) throws Exception {
        Object obj = null;
        try {
            obj = memcachedClient.get(key);
            logger.info("[MemcacheServiceImpl][get][key=" + key + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return obj;
    }

    /**
     * 删除 memcached 中的任何现有值。
     * 
     * @param key
     *            键
     * @return
     */
    public boolean delete(String key) throws Exception {
        return deleteExp(key);
    }

    /**
     * 删除 memcached 中的任何现有值。
     * 
     * @param key
     *            键
     * @return
     */
    private boolean deleteExp(String key) throws Exception {
        boolean flag = false;
        try {
            flag = memcachedClient.delete(key);
            logger.info("[MemcacheServiceImpl][delete][flag=" + flag + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return flag;
    }

    /**
     * 清理缓存中的所有键/值对
     * 
     * @return
     */
    public boolean flashAll() throws Exception {
        boolean flag = false;
        try {
            flag = memcachedClient.flushAll();
            logger.info("[MemcacheServiceImpl][flashAll][flag=" + flag + "]");
        } catch (Exception e) {
            String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
            logger.error(msg, e);
            throw new Exception(msg, e);
        }
        return flag;
    }

}
