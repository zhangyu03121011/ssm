package com.common.search.elasticsearch.service;

import java.util.List;

import org.elasticsearch.client.Client;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.common.base.constant.BaseConstant;

import io.searchbox.client.JestResult;
import io.searchbox.client.http.JestHttpClient;

/**
 * Elasticsearch Service
 * 
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
public interface IElasticsearchService<T> extends BaseConstant {

	/**
	 * 获取ES客户端
	 * 
	 * @return
	 * @throws Exception
	 */
	public JestHttpClient jestClient() throws Exception;

	/**
	 * 创建索引类型
	 * 
	 * @param indexName
	 * @throws Exception
	 */
	public void createIndex(String indexName) throws Exception;

	/**
	 * 删除索引数据
	 * 
	 * @param indexName
	 * @throws Exception
	 */
	public void deleteIndex(String indexName) throws Exception;

	/**
	 * 创建索引数据
	 * 
	 * @param indexName
	 * @param indexType
	 * @param obj
	 * @throws Exception
	 */
	public void builderIndex(String indexName, String indexType, T obj) throws Exception;

	/**
	 * 批量创建索引数据
	 * 
	 * @param indexName
	 * @param indexType
	 * @param list
	 * @throws Exception
	 */
	public void builderBulkIndex(String indexName, String indexType, List<T> list) throws Exception;

	/**
	 * 手动创建类型（mapping一旦定义创建，field只能新增，不能修改）
	 * 
	 * @param indexName
	 * @param indexType
	 * @param mappingStr
	 * @return
	 * @throws Exception
	 */
	public boolean createType(String indexName, String indexType, String mappingStr) throws Exception;

	/**
	 * 获取单个对象数据
	 * 
	 * @param indexName
	 * @param id
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public T get(String indexName, String id, Class<T> cls) throws Exception;

	/**
	 * 搜索索引数据
	 * 
	 * @param indexName
	 * @param indexType
	 * @param builder
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public List<T> search(String indexName, String indexType, SearchSourceBuilder builder, Class<T> cls)
			throws Exception;

	/**
	 * 搜索索引数据
	 * 
	 * @param indexName
	 * @param indexType
	 * @param builder
	 * @return
	 * @throws Exception
	 */
	public JestResult search(String indexName, String indexType, SearchSourceBuilder builder) throws Exception;

	/**
	 * 批量刪除索引数据
	 * 
	 * @param indexName
	 * @param indexType
	 * @param idList
	 * @throws Exception
	 */
	public void builderBulkDelete(String indexName, String indexType, List<String> idList) throws Exception;

	/**
	 * 获取ES客户端（ES原生方法）
	 * 
	 * @return
	 * @throws Exception
	 */
	public Client getSearchClient() throws Exception;
}
