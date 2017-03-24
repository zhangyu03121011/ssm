package com.common.orm.mybatis.dao.page;

import org.apache.ibatis.session.RowBounds;

import com.common.dao.base.IBaseDao;
import com.github.pagehelper.Page;

/**
 * 通用DAO接口定义了新增、修改、删除、查询单个记录、查询记录列表、分页查询列表的方法
 * 
 * @author: HuiJunLuo
 * @date:2015年12月28日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
public interface IBasePageHelperDao<T> extends IBaseDao<T> {

	/**
	 * 分页查询
	 * 
	 * @param params
	 * @param bounds
	 * @return
	 * @throws Exception
	 */
	public Page<T> getPage(T params, RowBounds bounds) throws Exception;

	/**
	 * 分页查询
	 * 
	 * @param obj
	 * @param bounds
	 * @return
	 * @throws Exception
	 */
	public Page<Object> getPageObject(Object obj, RowBounds bounds) throws Exception;

}
