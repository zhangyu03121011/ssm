package com.common.cache.redis.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.common.base.common.BaseLogger;
import com.common.cache.redis.service.IRedisService;
import com.common.util.ExceptionUtil;

/**
 * Redis Service
 * 
 * @author: HuiJunLuo
 * @date:2016年1月7日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
@Service
public class RedisServiceImpl extends BaseLogger implements IRedisService {

	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	public boolean delete(Serializable key) throws Exception {
		boolean flag = false;
		try {
			redisTemplate.delete(key);
			flag = true;
			logger.info("[RedisServiceImpl][delete][key=" + key + "][flag=" + flag + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return flag;
	}

	public boolean delete(Collection<Serializable> keys) throws Exception {
		boolean flag = false;
		try {
			redisTemplate.delete(keys);
			flag = true;
			logger.info("[RedisServiceImpl][delete][keys=" + keys + "][flag=" + flag + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return flag;
	}

	public void set(Serializable key, Serializable value, long expire) throws Exception {
		try {
			redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
			logger.info("[RedisServiceImpl][set][key=" + key + "][expire=" + expire + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	public void set(Serializable key, Serializable value) throws Exception {
		try {
			redisTemplate.opsForValue().set(key, value);
			logger.info("[RedisServiceImpl][set][key=" + key + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	public void set(Object objKey, Serializable key, Serializable value) throws Exception {
		try {
			redisTemplate.opsForHash().put(key, objKey, value);
			logger.info("[RedisServiceImpl][set][objKey=" + objKey + "][key=" + key + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	public Object get(Object objKey, Serializable key) throws Exception {
		Object obj = null;
		try {
			obj = redisTemplate.opsForHash().get(key, objKey);
			logger.info("[RedisServiceImpl][get][key=" + key + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return obj;
	}

	public Serializable get(Serializable key) throws Exception {
		Serializable obj = null;
		try {
			obj = redisTemplate.opsForValue().get(key);
			logger.info("[RedisServiceImpl][get][key=" + key + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return obj;
	}

	@Override
	public Set<Serializable> keys(String pattern) throws Exception {
		Set<Serializable> set = null;
		try {
			set = redisTemplate.keys(pattern);
			logger.info("[RedisServiceImpl][keys][pattern=" + pattern + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return set;
	}

	@Override
	public boolean exists(Serializable key) throws Exception {
		boolean flag = false;
		try {
			flag = redisTemplate.hasKey(key);
			logger.info("[RedisServiceImpl][exists][key=" + key + "][flag=" + flag + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return flag;
	}

	@Override
	public boolean flushDb() throws Exception {
		boolean flag = false;
		try {
			flag = redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					connection.flushDb();
					return true;
				}
			});
			logger.info("[RedisServiceImpl][flushDB][flag=" + flag + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return flag;
	}

	@Override
	public long dbSize() throws Exception {
		long size = 0;
		try {
			size = redisTemplate.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection) throws DataAccessException {
					return connection.dbSize();
				}
			});
			logger.info("[RedisServiceImpl][dbSize][size=" + size + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return size;
	}

	@Override
	public boolean ping() throws Exception {
		boolean flag = false;
		try {
			flag = redisTemplate.execute(new RedisCallback<Boolean>() {
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					String str = connection.ping();
					return str.equalsIgnoreCase("PONG");
				}
			});
			logger.info("[RedisServiceImpl][ping][flag=" + flag + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return flag;
	}

	@SuppressWarnings("rawtypes")
	public HashOperations redisHash() throws Exception {
		HashOperations hashOperations = null;
		try {
			hashOperations = redisTemplate.opsForHash();
			// logger.info("[RedisServiceImpl][redisHash]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return hashOperations;
	}

	@SuppressWarnings("rawtypes")
	public ListOperations redisList() throws Exception {
		ListOperations listOperations = null;
		try {
			listOperations = redisTemplate.opsForList();
			// logger.info("[RedisServiceImpl][redisList]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return listOperations;
	}

	@SuppressWarnings("rawtypes")
	public SetOperations redisSet() throws Exception {
		SetOperations setOperations = null;
		try {
			setOperations = redisTemplate.opsForSet();
			// logger.info("[RedisServiceImpl][redisSet]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return setOperations;
	}

	@SuppressWarnings("rawtypes")
	public ZSetOperations redisZSet() throws Exception {
		ZSetOperations zSetOperations = null;
		try {
			zSetOperations = redisTemplate.opsForZSet();
			// logger.info("[RedisServiceImpl][redisZSet]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return zSetOperations;
	}

	@SuppressWarnings("rawtypes")
	public ValueOperations redisValue() throws Exception {
		ValueOperations valueOperations = null;
		try {
			valueOperations = redisTemplate.opsForValue();
			// logger.info("[RedisServiceImpl][redisValue]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return valueOperations;
	}

	/**
	 * redisTemplate
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public RedisTemplate redisTemplate() throws Exception {
		return redisTemplate;
	}

}
