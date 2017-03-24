package com.common.orm.mybatis.service.page.impl;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.base.vo.base.PageListVo;
import com.common.orm.mybatis.dao.page.IBasePageHelperDao;
import com.common.orm.mybatis.service.page.IBasePageHelperService;
import com.common.service.service.base.impl.BaseServiceImpl;
import com.common.util.ExceptionUtil;
import com.github.pagehelper.Page;

/**
 * 通用Service接口定义了新增、修改、删除、查询单个记录、查询记录列表、分页查询列表的方法
 * 
 * @author: HuiJunLuo
 * @date:2015年12月28日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 * @param <E>
 */
public class BasePageHelperServiceImpl<T, E extends IBasePageHelperDao<T>> extends BaseServiceImpl<T, E>
		implements IBasePageHelperService<T> {

	@Autowired
	private E baseDao;

	@Override
	public PageListVo<T> getPage(T entity, PageListVo<T> page) throws Exception {
		PageListVo<T> list = null;
		try {
			RowBounds rowBounds = new RowBounds(page.getCurrPage(), page.getPageSize());
			Page<T> pageList = baseDao.getPage(entity, rowBounds);
			list = new PageListVo<T>(pageList.getTotal(), pageList, page.getCurrPage(), page.getPageSize());
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
			RowBounds rowBounds = new RowBounds(page.getCurrPage(), page.getPageSize());
			Page<Object> pageList = baseDao.getPageObject(obj, rowBounds);
			list = new PageListVo<Object>(pageList.getTotal(), pageList, page.getCurrPage(), page.getPageSize());
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return list;
	}

}
