package com.common.orm.mybatis.service.page.impl;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.common.api.page.IBasePageMybatisReferenceApi;
import com.common.base.model.base.BaseModel;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.orm.mybatis.service.page.IBasePageMybatisApiService;
import com.common.service.service.base.impl.BaseApiServiceImpl;
import com.common.util.ExceptionUtil;

/**
 * 业务层通用方法
 * 
 * @author: HuiJunLuo
 * @date:2016年1月14日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
public abstract class BasePageMybatisApiServiceImpl<T extends BaseModel, E extends IBasePageMybatisReferenceApi<T>>
		extends BaseApiServiceImpl<T, E> implements IBasePageMybatisApiService<T> {

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
			pageListVo = this.getPage(obj, pageListVo);

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
			pageListVo = this.getPage(obj, pageListVo);

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
