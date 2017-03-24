package com.common.orm.mybatis.api.page.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.common.api.page.IBasePageHelperProviderApi;
import com.common.base.vo.base.PageListVo;
import com.common.orm.mybatis.service.page.IBasePageHelperService;
import com.common.service.service.api.impl.BaseProviderApiImpl;
import com.common.util.ExceptionUtil;

/**
 * 通用Api接口定义了新增、修改、删除、查询单个记录、查询记录列表、分页查询列表的方法（用于JSW工程的实现）
 * 
 * @author: HuiJunLuo
 * @date:2015年12月28日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 * @param <E>
 */
public class BasePageHelperProviderApiImpl<T, E extends IBasePageHelperService<T>> extends BaseProviderApiImpl<T, E>
		implements IBasePageHelperProviderApi<T> {

	@Autowired
	private E baseService;

	@Override
	public PageListVo<T> getPage(T entity, PageListVo<T> page) throws Exception {
		PageListVo<T> list = null;
		try {
			list = baseService.getPage(entity, page);
			logger.debug(
					"[" + this.getClass().getSimpleName() + "][getPage][entity=" + JSON.toJSONString(entity) + "]");
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
			list = baseService.getPageObject(obj, page);
			logger.debug(
					"[" + this.getClass().getSimpleName() + "][getPageObject][entity=" + JSON.toJSONString(obj) + "]");
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
		return list;
	}

}
