package com.common.search.elasticsearch.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.common.base.common.BaseLogger;
import com.common.search.elasticsearch.service.IElasticsearchService;
import com.common.util.ExceptionUtil;

import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.client.http.JestHttpClient;
import io.searchbox.core.Bulk;
import io.searchbox.core.BulkResult;
import io.searchbox.core.Delete;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.PutMapping;

/**
 * Elasticsearch Service
 * 
 * @author: HuiJunLuo
 * @date:2015年12月31日
 * @Copyright:Copyright (c) xxxx有限公司 2014 ~ 2015 版权所有
 * @param <T>
 */
@Service
public class ElasticsearchServiceImpl<T> extends BaseLogger implements IElasticsearchService<T> {

	public static JestHttpClient jestClient;

	@Value("${es.url}")
	private String esUrl;

	@Value("${es.clusterName}")
	private String esClusterName;

	@Value("${es.ip}")
	private String esIp;

	@Value("${es.port}")
	private int esPort;

	/**
	 * 获取ES客户端
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public synchronized JestHttpClient jestClient() throws Exception {
		try {
			// es配置
			HttpClientConfig httpClientConfig = new HttpClientConfig.Builder(esUrl).multiThreaded(true)
					.connTimeout(30 * 60 * 1000).readTimeout(10 * 60 * 1000)
					.maxConnectionIdleTime(30 * 60, TimeUnit.SECONDS).defaultMaxTotalConnectionPerRoute(10)
					.maxTotalConnection(15).build();

			// es客户端对象
			JestClientFactory jestClientFactory = new JestClientFactory();
			jestClientFactory.setHttpClientConfig(httpClientConfig);
			if (jestClient == null) {
				jestClient = (JestHttpClient) jestClientFactory.getObject();
			}
			return jestClient;
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/**
	 * 创建索引类型
	 * 
	 * @param indexName
	 * @throws Exception
	 */
	@Override
	public void createIndex(String indexName) throws Exception {
		try {
			// 创建索引
			CreateIndex createIndex = new CreateIndex.Builder(indexName).build();
			jestClient().execute(createIndex);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/**
	 * 删除索引数据
	 * 
	 * @param indexName
	 * @throws Exception
	 */
	@Override
	public void deleteIndex(String indexName) throws Exception {
		try {
			// 如果索引存在则删除索引
			DeleteIndex deleteIndex = new DeleteIndex.Builder(indexName).build();
			jestClient().execute(deleteIndex);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/**
	 * 创建索引数据
	 * 
	 * @param indexName
	 * @param indexType
	 * @param obj
	 * @throws Exception
	 */
	@Override
	public void builderIndex(String indexName, String indexType, Object obj) throws Exception {
		try {
			Index index = new Index.Builder(obj).index(indexName).type(indexType).build();
			jestClient().execute(index);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/**
	 * 批量创建索引数据
	 * 
	 * @param indexName
	 * @param indexType
	 * @param list
	 * @throws Exception
	 */
	@Override
	public void builderBulkIndex(String indexName, String indexType, List<T> list) throws Exception {
		try {
			// Bulk 2个参数：1-索引名称，2-类型名称
			Bulk.Builder bulk = new Bulk.Builder();
			// 添加数据到ES
			for (T obj : list) {
				bulk.addAction(new Index.Builder(obj).index(indexName).type(indexType).build());
			}
			BulkResult bulkResult = jestClient().execute(bulk.build());
			logger.info("bulk index---->" + bulkResult.isSucceeded() + "----->" + bulkResult.getErrorMessage());
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/**
	 * 手动创建类型（mapping一旦定义创建，field只能新增，不能修改）
	 * 
	 * @param indexName
	 * @param indexType
	 * @param mappingStr
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean createType(String indexName, String indexType, String mappingStr) throws Exception {
		try {
			PutMapping.Builder builder = new PutMapping.Builder(indexName, indexType, mappingStr);
			builder.refresh(true);
			JestResult jestResult = jestClient().execute(builder.build());
			if (jestResult == null || !jestResult.isSucceeded()) {
				throw new RuntimeException(jestResult.getErrorMessage() + "创建索引类型失败");
			}
			return true;
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/**
	 * 获取单个对象数据
	 * 
	 * @param indexName
	 * @param id
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	@Override
	public T get(String indexName, String id, Class<T> cls) throws Exception {
		try {
			Get get = new Get.Builder(indexName, id).build();
			JestResult jestResult = jestClient().execute(get);
			return jestResult.getSourceAsObject(cls);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

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
	@Override
	public List<T> search(String indexName, String indexType, SearchSourceBuilder builder, Class<T> cls)
			throws Exception {
		try {
			Search search = new Search.Builder(builder.toString()).addIndex(indexName).addType(indexType).build();
			JestResult jestResult = jestClient().execute(search);
			return jestResult.getSourceAsObjectList(cls);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/**
	 * 搜索索引数据
	 * 
	 * @param indexName
	 * @param indexType
	 * @param builder
	 * @return
	 * @throws Exception
	 */
	@Override
	public JestResult search(String indexName, String indexType, SearchSourceBuilder builder) throws Exception {
		try {
			Search search = new Search.Builder(builder.toString()).addIndex(indexName).addType(indexType).build();
			JestResult jestResult = jestClient().execute(search);
			return jestResult;
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/**
	 * 批量刪除索引数据
	 * 
	 * @param indexName
	 * @param indexType
	 * @param idList
	 * @throws Exception
	 */
	@Override
	public void builderBulkDelete(String indexName, String indexType, List<String> idList) throws Exception {
		try {
			// Bulk 2个参数：1-索引名称，2-类型名称
			Bulk.Builder bulk = new Bulk.Builder();
			// 添加数据到ES
			for (String id : idList) {
				bulk.addAction(new Delete.Builder(id).index(indexName).type(indexType).build());
			}
			BulkResult bulkResult = jestClient().execute(bulk.build());
			logger.info("bulk index---->" + bulkResult.isSucceeded() + "----->" + bulkResult.getErrorMessage());
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	/**
	 * 获取ES客户端（ES原生方法）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public Client getSearchClient() throws Exception {
		try {
			return getSearchClient(false);
		} catch (Exception e) {
			String msg = ExceptionUtil.getInstance().getExceptionMsg(e);
			logger.error(msg, e);
			throw new Exception(msg, e);
		}
	}

	public Client getSearchClient(boolean asNode) throws UnknownHostException {
		if (asNode) {
			Node node = NodeBuilder.nodeBuilder().clusterName(esClusterName).client(true).node();
			Client client = node.client();
			return client;
		} else {
			Settings settings = Settings.builder().put("cluster.name", esClusterName)
					.put("client.transport.sniff", true).build();
			InetAddress inetAddress = InetAddress.getByName(esIp);
			TransportClient client = TransportClient.builder().settings(settings).build();
			client.addTransportAddress(new InetSocketTransportAddress(inetAddress, esPort));
			return client;
		}
	}

}
