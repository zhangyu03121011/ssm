package com.common.service.service.base.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.base.common.BaseLogger;
import com.common.base.constant.BaseConstant;
import com.common.base.vo.base.PageListVo;
import com.common.dao.base.IBaseDao;
import com.common.service.service.base.IBaseService;
import com.common.util.ExceptionUtil;

/**
 * 通用Service接口定义了新增、修改、删除、查询单个记录、查询记录列表、分页查询列表的方法
 * 
 * @author: HuiJunLuo
 * @date:2015年12月28日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 * @param <E>
 */
public class BaseServiceImpl<T, E extends IBaseDao<T>> extends BaseLogger implements IBaseService<T>, BaseConstant {

	@Autowired
	private E baseDao;

	@Override
	public int insert(T entity) throws Exception {
		int result = 0;
		try {
			result = baseDao.insert(entity);
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
			result = baseDao.update(entity);
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
			result = baseDao.deleteById(id);
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
			result = baseDao.delete(entity);
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
			obj = baseDao.getOneById(id);
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
			obj = baseDao.getOne(entity);
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
			result = baseDao.getOneObject(obj);
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
			list = baseDao.getAll(entity);
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
			list = baseDao.getAllObject(obj);
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
			result = baseDao.getCount(entity);
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
			result = baseDao.getCountObject(obj);
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
			int startRow = (page.getCurrPage() - 1) * page.getPageSize();
			int endRow = startRow + page.getPageSize();
			List<T> pageList = baseDao.getPage(entity, startRow, endRow);
			long count = baseDao.getCount(entity);
			list = new PageListVo<T>(count, pageList, page.getCurrPage(), page.getPageSize());
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
			int startRow = (page.getCurrPage() - 1) * page.getPageSize();
			int endRow = startRow + page.getPageSize();
			List<Object> pageList = baseDao.getPageObject(obj, startRow, endRow);
			long count = baseDao.getCountObject(obj);
			list = new PageListVo<Object>(count, pageList, page.getCurrPage(), page.getPageSize());
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return list;
	}

}
