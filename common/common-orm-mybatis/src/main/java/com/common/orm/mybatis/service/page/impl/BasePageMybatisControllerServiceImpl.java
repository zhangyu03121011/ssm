package com.common.orm.mybatis.service.page.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.common.base.model.base.BaseModel;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.dao.page.IBasePageMybatisDao;
import com.common.service.service.base.IBaseControllerService;
import com.common.service.service.base.impl.BaseControllerServiceImpl;
import com.common.util.ExceptionUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 业务层通用方法
 * 
 * @author: HuiJunLuo
 * @date:2016年1月14日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
public class BasePageMybatisControllerServiceImpl<T extends BaseModel, E extends IBasePageMybatisDao<T>>
		extends BaseControllerServiceImpl<T, E> implements IBaseControllerService<T> {

	@Autowired
	private E baseDao;

	/**
	 * 查询分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public ResultVo<T> list(T obj, PageListVo<T> pageListVo, HttpServletRequest request) throws Exception {
		ResultVo<T> resultVo = new ResultVo<T>();
		int res = RESULT_FAILURE;
		try {

			// 查询信息
			PageBounds pageBounds = new PageBounds(pageListVo.getCurrPage(), pageListVo.getPageSize());
			PageList<T> pageList = baseDao.getPage(obj, pageBounds);
			pageListVo = new PageListVo<T>(pageList.getPaginator().getTotalCount(), pageList, pageListVo.getCurrPage(),
					pageListVo.getPageSize());

			res = RESULT_SUCCESS;
			resultVo.setObj(obj);
			resultVo.setPageListVo(pageListVo);

			logger.debug("[" + this.getClass().getSimpleName() + "][list][" + obj.getClass().getSimpleName() + "="
					+ JSON.toJSONString(obj) + "][res=" + res + "]");
		} catch (Exception e) {
			res = RESULT_EXCEPTION;
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		resultVo.setRes(res);
		return resultVo;
	}

	/**
	 * 查询分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public PageListVo<T> page(T obj, PageListVo<T> pageListVo, HttpServletRequest request) throws Exception {
		try {
			// 查询信息
			PageBounds pageBounds = new PageBounds(pageListVo.getCurrPage(), pageListVo.getPageSize());
			PageList<T> pageList = baseDao.getPage(obj, pageBounds);
			pageListVo = new PageListVo<T>(pageList.getPaginator().getTotalCount(), pageList, pageListVo.getCurrPage(),
					pageListVo.getPageSize());

			logger.debug("[" + this.getClass().getSimpleName() + "][page][" + obj.getClass().getSimpleName() + "="
					+ JSON.toJSONString(obj) + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return pageListVo;
	}

}
