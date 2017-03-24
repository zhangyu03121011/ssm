package com.common.cache.memcache.service;

import com.common.base.constant.BaseConstant;

/**
 * Memcache Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月20日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IMemcacheService extends BaseConstant {

    /**
     * 向缓存添加新的键值对。如果键已经存在，则之前的值将被替换。
     * 
     * @param key
     *            键
     * @param value
     *            值
     * @return
     */
    public boolean set(String key, Object value) throws Exception;

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
    public boolean set(String key, Object value, long expire) throws Exception;

    /**
     * 仅当缓存中不存在键时，add 命令才会向缓存中添加一个键值对。
     * 
     * @param key
     *            键
     * @param value
     *            值
     * @return
     */
    public boolean add(String key, Object value) throws Exception;

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
    public boolean add(String key, Object value, long expire) throws Exception;

    /**
     * 仅当键已经存在时，replace 命令才会替换缓存中的键。
     * 
     * @param key
     *            键
     * @param value
     *            值
     * @return
     */
    public boolean replace(String key, Object value) throws Exception;

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
    public boolean replace(String key, Object value, long expire) throws Exception;

    /**
     * get 命令用于检索与之前添加的键值对相关的值。
     * 
     * @param key
     *            键
     * @return
     */
    public Object get(String key) throws Exception;

    /**
     * 删除 memcached 中的任何现有值。
     * 
     * @param key
     *            键
     * @return
     */
    public boolean delete(String key) throws Exception;

    /**
     * 清理缓存中的所有键/值对
     * 
     * @return
     */
    public boolean flashAll() throws Exception;

}
