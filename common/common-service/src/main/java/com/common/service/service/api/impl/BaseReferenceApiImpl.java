package com.common.service.service.api.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.api.base.IBaseApi;
import com.common.base.common.BaseLogger;
import com.common.base.vo.base.PageListVo;
import com.common.util.ExceptionUtil;

/**
 * 通用Api接口定义了新增、修改、删除、查询单个记录、查询记录列表、分页查询列表的方法（用于调用工程的实现）
 * 
 * @author: HuiJunLuo
 * @date:2015年12月28日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 * @param <E>
 */
public class BaseReferenceApiImpl<T, E extends IBaseApi<T>> extends BaseLogger implements IBaseApi<T> {
    
	@Autowired
	private E baseApi;

	@Override
	public int insert(T entity) throws Exception {
		int result = 0;
		try {
			result = baseApi.insert(entity);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return result;
	}

	@Override
	public int update(T entity) throws Exception {
		int result = 0;
		try {
			result = baseApi.update(entity);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return result;
	}

	@Override
	public int deleteById(Serializable id) throws Exception {
		int result = 0;
		try {
			result = baseApi.deleteById(id);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return result;
	}

	@Override
	public int delete(T entity) throws Exception {
		int result = 0;
		try {
			result = baseApi.delete(entity);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return result;
	}

	@Override
	public T getOneById(Serializable id) throws Exception {
		T obj = null;
		try {
			obj = baseApi.getOneById(id);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return obj;
	}

	@Override
	public T getOne(T entity) throws Exception {
		T obj = null;
		try {
			obj = baseApi.getOne(entity);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return obj;
	}

	@Override
	public Object getOneObject(Object obj) throws Exception {
		Object result = null;
		try {
			result = baseApi.getOneObject(obj);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return result;
	}

	@Override
	public List<T> getAll(T entity) throws Exception {
		List<T> list = null;
		try {
			list = baseApi.getAll(entity);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return list;
	}

	@Override
	public List<Object> getAllObject(Object obj) throws Exception {
		List<Object> list = null;
		try {
			list = baseApi.getAllObject(obj);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return list;
	}

	@Override
	public long getCount(T entity) throws Exception {
		long result = 0;
		try {
			result = baseApi.getCount(entity);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return result;
	}

	@Override
	public long getCountObject(Object obj) throws Exception {
		long result = 0;
		try {
			result = baseApi.getCountObject(obj);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return result;
	}

	@Override
	public PageListVo<T> getPage(T entity, PageListVo<T> page) throws Exception {
		PageListVo<T> list = null;
		try {
			list = baseApi.getPage(entity, page);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return list;
	}

	@Override
	public PageListVo<Object> getPageObject(Object obj, PageListVo<Object> page) throws Exception {
		PageListVo<Object> list = null;
		try {
			list = baseApi.getPageObject(obj, page);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return list;
	}

}
