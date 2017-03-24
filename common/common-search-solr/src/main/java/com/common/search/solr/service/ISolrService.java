package com.common.search.solr.service;

import java.io.Serializable;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;

import com.common.base.constant.BaseConstant;
import com.common.base.vo.base.PageListVo;

/**
 * Solr Service
 * 
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
public interface ISolrService<T> extends BaseConstant {

	/**
	 * 获取Sor数据
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public T getOneById(Serializable id, String collection, Class<T> entityClass) throws Exception;

	/**
	 * 获取Sor数据
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public List<T> list(String query, String collection, Class<T> entityClass) throws Exception;

	/**
	 * 添加Sor数据
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void saveOrUpdate(T entity, String collection) throws Exception;

	/**
	 * 批量添加或更新Sor列表数据
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void batch(List<T> list, String collection) throws Exception;

	/**
	 * 根据ID删除Solr数据
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(Serializable id, String collection) throws Exception;

	/**
	 * 根据查询条件删除Solr数据
	 * 
	 * @param query
	 * @throws Exception
	 */
	public void delete(String query, String collection) throws Exception;

	/**
	 * 删除Solr列表数据
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void delete(List<String> ids, String collection) throws Exception;

	/**
	 * 查询Solr分页数据
	 * 
	 * @param entity
	 * @param pageListVo
	 * @return
	 * @throws Exception
	 */
	public PageListVo<T> page(SolrQuery solrQuery, PageListVo<T> pageListVo, Class<T> className, String collection)
			throws Exception;

}
