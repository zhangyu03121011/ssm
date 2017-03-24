package com.common.service.service.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.common.api.base.IBaseApi;
import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;

/**
 * 业务层通用方法
 * 
 * @author: HuiJunLuo
 * @date:2016年1月14日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
public interface IBaseApiService<T> extends IBaseApi<T> {

	/**
	 * 查询所有数据
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<T> list(T obj, HttpServletRequest request) throws Exception;

	/**
	 * 查询分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public ResultVo<T> list(T obj, PageListVo<T> pageListVo, HttpServletRequest request) throws Exception;

	/**
	 * 查询分页
	 * 
	 * @param obj
	 * @param page
	 * @param request
	 * @return
	 */
	public PageListVo<T> page(T obj, PageListVo<T> pageListVo, HttpServletRequest request) throws Exception;

	/**
	 * 单个信息查询
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<T> single(T obj, HttpServletRequest request) throws Exception;

	/**
	 * 查询明细
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<T> detail(String id, HttpServletRequest request) throws Exception;

	/**
	 * 查询数量
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<T> count(T obj, HttpServletRequest request) throws Exception;

	/**
	 * 更新数据
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<T> update(T obj, HttpServletRequest request) throws Exception;

	/**
	 * 批量更新数据
	 * 
	 * @param objList
	 * @param request
	 * @return
	 */
	public ResultVo<T> batch(List<T> objList, HttpServletRequest request) throws Exception;

	/**
	 * 保存数据
	 * 
	 * @param obj
	 * @param request
	 * @return
	 */
	public ResultVo<T> save(T obj, HttpServletRequest request) throws Exception;

}
