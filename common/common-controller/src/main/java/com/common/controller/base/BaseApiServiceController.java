package com.common.controller.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.base.annotation.ListBody;
import com.common.base.model.base.BaseModel;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.service.service.base.IBaseApiService;
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
public class BaseApiServiceController<T extends BaseModel, E extends IBaseApiService<T>> extends BaseController {

	@Autowired
	private E objService;
	
	/**
	 * 查询列表
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list")
	public ResultVo<T> list(T obj, HttpServletRequest request) {
		ResultVo<T> resultVo = new ResultVo<>();
		try {
			resultVo = objService.list(obj, request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
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

	/**
	 * 单个信息查询
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/single")
	public ResultVo<T> single(T obj, HttpServletRequest request) {
		ResultVo<T> resultVo = new ResultVo<>();
		try {
			resultVo = objService.single(obj, request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 查询明细
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/detail")
	public ResultVo<T> detail(String id, HttpServletRequest request) {
		ResultVo<T> resultVo = new ResultVo<>();
		try {
			resultVo = objService.detail(id, request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 查询数量
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/count")
	public ResultVo<T> count(T obj, HttpServletRequest request) {
		ResultVo<T> resultVo = new ResultVo<>();
		try {
			resultVo = objService.count(obj, request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 更新数据
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public ResultVo<T> update(T obj, HttpServletRequest request) {
		ResultVo<T> resultVo = new ResultVo<>();
		try {
			resultVo = objService.update(obj, request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 批量更新数据
	 * 
	 * @param list
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/batch")
	public ResultVo<T> batch(@ListBody List<T> list, HttpServletRequest request) {
		ResultVo<T> resultVo = new ResultVo<>();
		try {
			resultVo = objService.batch(list, request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

	/**
	 * 保存数据
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/save")
	public ResultVo<T> save(T obj, HttpServletRequest request) {
		ResultVo<T> resultVo = new ResultVo<>();
		try {
			resultVo = objService.save(obj, request);
		} catch (Exception e) {
			resultVo.setRes(RESULT_EXCEPTION);
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
		}
		return resultVo;
	}

}