package com.common.orm.mybatis.service.page.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.base.vo.base.PageListVo;
import com.common.orm.mybatis.dao.page.IBasePageMybatisDao;
import com.common.orm.mybatis.service.page.IBasePageMybatisService;
import com.common.service.service.base.impl.BaseServiceImpl;
import com.common.util.ExceptionUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 通用Service接口定义了新增、修改、删除、查询单个记录、查询记录列表、分页查询列表的方法
 * 
 * @author: HuiJunLuo
 * @date:2015年12月28日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 * @param <E>
 */
public class BasePageMybatisServiceImpl<T, E extends IBasePageMybatisDao<T>> extends BaseServiceImpl<T, E>
		implements IBasePageMybatisService<T> {

	@Autowired
	private E baseDao;

	@Override
	public PageListVo<T> getPage(T entity, PageListVo<T> page) throws Exception {
		PageListVo<T> list = null;
		try {
			PageBounds pageBounds = new PageBounds(page.getCurrPage(), page.getPageSize());
			PageList<T> pageList = baseDao.getPage(entity, pageBounds);
			list = new PageListVo<T>(pageList.getPaginator().getTotalCount(), pageList, page.getCurrPage(),
					page.getPageSize());
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
			PageBounds pageBounds = new PageBounds(page.getCurrPage(), page.getPageSize());
			PageList<Object> pageList = baseDao.getPageObject(obj, pageBounds);
			list = new PageListVo<Object>(pageList.getPaginator().getTotalCount(), pageList, page.getCurrPage(),
					page.getPageSize());
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return list;
	}

}
