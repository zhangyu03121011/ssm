package com.common.cache.redis.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import com.common.base.constant.BaseConstant;

/**
 * Redis Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月20日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 */
public interface IRedisService extends BaseConstant {

    /**
     * 通过key删除
     * 
     * @param key
     */
    public abstract boolean delete(Serializable key) throws Exception;

    /**
     * 通过key删除
     * 
     * @param key
     */
    public abstract boolean delete(Collection<Serializable> keys) throws Exception;

    /**
     * 添加key value 并且设置存活时间(value)
     * 
     * @param key
     * @param value
     * @param expire
     *            单位秒
     */
    public abstract void set(Serializable key, Serializable value, long expire) throws Exception;

    /**
     * 添加key value(value)
     * 
     * @param key
     * @param value
     */
    public abstract void set(Serializable key, Serializable value) throws Exception;

    /**
     * 获取redis value (value)
     * 
     * @param key
     * @return
     */
    public abstract Serializable get(Serializable key) throws Exception;

    /**
     * 添加key value(hash)
     * 
     * @param key
     * @param value
     */
    public abstract void set(Object objKey, Serializable key, Serializable value) throws Exception;

    /**
     * 获取key value(hash)
     * 
     * @param key
     * @param value
     */
    public abstract Object get(Object objKey, Serializable key) throws Exception;

    /**
     * 通过正则匹配keys
     * 
     * @param pattern
     * @return
     */
    public abstract Set<Serializable> keys(String pattern) throws Exception;

    /**
     * 检查key是否已经存在
     * 
     * @param key
     * @return
     */
    public abstract boolean exists(Serializable key) throws Exception;

    /**
     * 清空redis 所有数据
     * 
     * @return
     */
    public abstract boolean flushDb() throws Exception;

    /**
     * 查看redis里有多少数据
     */
    public abstract long dbSize() throws Exception;

    /**
     * 检查是否连接成功
     * 
     * @return
     */
    public abstract boolean ping() throws Exception;

    /**
     * Hash对象
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    public HashOperations redisHash() throws Exception;

    /**
     * List对象
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    public ListOperations redisList() throws Exception;

    /**
     * Set对象
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    public SetOperations redisSet() throws Exception;

    /**
     * ZSet对象
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    public ZSetOperations redisZSet() throws Exception;

    /**
     * Value对象
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    public ValueOperations redisValue() throws Exception;
    
    /**
     * redisTemplate
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    public RedisTemplate redisTemplate() throws Exception;
}
