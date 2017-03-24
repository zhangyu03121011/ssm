package com.common.orm.mybatis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.model.base.BaseModel;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.controller.base.BaseApiServiceController;
import com.common.orm.mybatis.service.page.IBasePageMybatisApiService;
import com.common.util.ExceptionUtil;

/**
 * 公共Controller，提供一些公共功能
 * 
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 * @param <E>
 */
public class BasePageMybatisApiServiceController<T extends BaseModel, E extends IBasePageMybatisApiService<T>>
		extends BaseApiServiceController<T, E> {

	@Autowired
	private E objService;

	/**
	 * 查询分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/page")
	public ResultVo<T> page(T obj, PageListVo<T> page, HttpServletRequest request) {
		ResultVo<T> resultVo = new ResultVo<>();
		try {
			resultVo = objService.list(obj, page, request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 查询分页（用于easyUI框架）
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pagination")
	public PageListVo<T> pagination(T obj, PageListVo<T> page, HttpServletRequest request) {
		PageListVo<T> resultVo = new PageListVo<>();
		try {
			resultVo = objService.page(obj, page, request);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

}