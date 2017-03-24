package com.common.api.base;

import java.io.Serializable;
import java.util.List;

import com.common.base.constant.BaseConstant;
import com.common.base.vo.base.PageListVo;

/**
 * 通用Api接口定义了新增、修改、删除、查询单个记录、查询记录列表、分页查询列表的方法
 * 
 * @author: HuiJunLuo
 * @date:2015年12月28日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
public interface IBaseApi<T> extends BaseConstant {

	/**
	 * 插入数据
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int insert(T entity) throws Exception;

	/**
	 * 更新数据
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int update(T entity) throws Exception;

	/**
	 * 删除数据
	 * 
	 * @param id
	 * @throws Exception
	 */
	public int deleteById(Serializable id) throws Exception;

	/**
	 * 删除数据
	 * 
	 * @param id
	 * @throws Exception
	 */
	public int delete(T entity) throws Exception;

	/**
	 * 根据id查询单个记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T getOneById(Serializable id) throws Exception;

	/**
	 * 根据对象查询单个记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T getOne(T entity) throws Exception;

	/**
	 * 根据对象查询信息
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object getOneObject(Object obj) throws Exception;

	/**
	 * 获取所有记录
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public List<T> getAll(T entity) throws Exception;

	/**
	 * 获取所有记录
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public List<Object> getAllObject(Object obj) throws Exception;

	/**
	 * 查询总数
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public long getCount(T entity) throws Exception;

	/**
	 * 查询总数
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public long getCountObject(Object obj) throws Exception;

	/**
	 * 分页查询
	 * 
	 * @param entity
	 * @param pageListVo
	 * @return
	 * @throws Exception
	 */
	public PageListVo<T> getPage(T entity, PageListVo<T> pageListVo) throws Exception;

	/**
	 * 分页查询
	 * 
	 * @param entity
	 * @param pageListVo
	 * @return
	 * @throws Exception
	 */
	public PageListVo<Object> getPageObject(Object obj, PageListVo<Object> pageListVo) throws Exception;

}
