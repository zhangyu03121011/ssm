package com.common.orm.mybatis.service.page;

import javax.servlet.http.HttpServletRequest;

import com.common.base.vo.base.PageListVo;
import com.common.base.vo.base.ResultVo;
import com.common.service.service.base.IBaseApiService;

/**
 * 业务层通用方法（用于API调用的Service）
 * 
 * @author: HuiJunLuo
 * @date:2016年1月14日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
public interface IBasePageHelperApiService<T> extends IBaseApiService<T> {

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

}
