package com.common.orm.mybatis.dao.page;

import com.common.dao.base.IBaseDao;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 通用DAO接口定义了新增、修改、删除、查询单个记录、查询记录列表、分页查询列表的方法
 * 
 * @author: HuiJunLuo
 * @date:2015年12月28日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
public interface IBasePageMybatisDao<T> extends IBaseDao<T> {

	/**
	 * 获取分页结果
	 * 
	 * @param params
	 * @param pageBounds
	 * @return
	 * @throws Exception
	 */
	public PageList<T> getPage(T params, PageBounds pageBounds) throws Exception;

	/**
	 * 获取分页结果
	 * 
	 * @param obj
	 * @param pageBounds
	 * @return
	 * @throws Exception
	 */
	public PageList<Object> getPageObject(Object obj, PageBounds pageBounds) throws Exception;

}
