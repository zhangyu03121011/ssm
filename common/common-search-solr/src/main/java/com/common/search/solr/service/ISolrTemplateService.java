package com.common.search.solr.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SolrDataQuery;

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
public interface ISolrTemplateService<T> extends BaseConstant {

	/**
	 * 查询Sor数据
	 * 
	 * @param query
	 * @throws Exception
	 */
	public T getOne(SimpleQuery query) throws Exception;

	/**
	 * 查询Sor数据
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public T getOneById(Serializable id) throws Exception;

	/**
	 * 添加Sor数据
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void saveOrUpdate(T entity) throws Exception;

	/**
	 * 批量添加或更新Sor列表数据
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void batch(List<T> list) throws Exception;

	/**
	 * 根据ID删除Solr数据
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(Serializable id) throws Exception;

	/**
	 * 根据查询条件删除Solr数据
	 * 
	 * @param query
	 * @throws Exception
	 */
	public void delete(SolrDataQuery query) throws Exception;

	/**
	 * 删除Solr列表数据
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void delete(List<String> ids) throws Exception;

	/**
	 * 查询Solr分页数据
	 * 
	 * @param entity
	 * @param pageListVo
	 * @return
	 * @throws Exception
	 */
	public PageListVo<T> page(Query query, PageListVo<T> pageListVo) throws Exception;

}
